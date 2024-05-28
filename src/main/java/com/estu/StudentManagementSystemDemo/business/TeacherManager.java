package com.estu.StudentManagementSystemDemo.business;

import com.estu.StudentManagementSystemDemo.business.dtos.requests.teacherRequests.CreateTeacherRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.teacherRequests.DeleteTeacherRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.teacherRequests.UpdateTeacherRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.teacherResponses.GetAllTeacherResponse;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.teacherResponses.GetAllTeachersByClassroom;
import com.estu.StudentManagementSystemDemo.dataAccess.ClassroomRepository;
import com.estu.StudentManagementSystemDemo.dataAccess.TeacherRepository;
import com.estu.StudentManagementSystemDemo.entities.Classroom;
import com.estu.StudentManagementSystemDemo.entities.Student;
import com.estu.StudentManagementSystemDemo.entities.Teacher;
import com.estu.StudentManagementSystemDemo.utilities.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherManager {
    private final TeacherRepository teacherRepository;
    private final ModelMapperService modelMapperService;
    private final ClassroomRepository classroomRepository; // Add this

    public void createTeacher(CreateTeacherRequest createTeacherRequest) {
        Teacher teacher = this.modelMapperService.forRequest().map(createTeacherRequest, Teacher.class);
        List<Classroom> classrooms = classroomRepository.findAllByNameIn(createTeacherRequest.getClassroomNames());
        teacher.setClassrooms(classrooms);
        this.teacherRepository.save(teacher);
    }

    public List<GetAllTeacherResponse> getAll() {
        List<Teacher> teachers = this.teacherRepository.findAll();
        List<GetAllTeacherResponse> responses = teachers.stream()
                .map(GetAllTeacherResponse::new)
                .collect(Collectors.toList());
        return responses;
    }

    public void updateTeacher(int id, UpdateTeacherRequest updateTeacherRequest) {

        Teacher teacher = this.modelMapperService.forRequest().map(updateTeacherRequest, Teacher.class);
        List<Classroom> classrooms = classroomRepository.findAllByNameIn(updateTeacherRequest.getClassroomNames());
        teacher.setClassrooms(classrooms);
        teacher.setId(id);

        this.teacherRepository.save(teacher);


    }

    public void deleteTeacher(DeleteTeacherRequest deleteTeacherRequest) {
        Teacher teacher = teacherRepository.findById(deleteTeacherRequest.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + deleteTeacherRequest.getId()));
        teacherRepository.delete(teacher);
    }

    public List<GetAllTeachersByClassroom> getAllTeachersByClassroom(int classroomId) {

        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + classroomId));

        List<Teacher> teachers = this.teacherRepository.findByClassrooms(classroom);
        List<GetAllTeachersByClassroom> responses = teachers.stream()
                .map(teacher -> this.modelMapperService.forResponse()
                        .map(teacher, GetAllTeachersByClassroom.class))
                .collect(Collectors.toList());

        return responses;
    }
}
