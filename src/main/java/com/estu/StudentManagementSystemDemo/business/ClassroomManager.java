package com.estu.StudentManagementSystemDemo.business;

import com.estu.StudentManagementSystemDemo.business.dtos.requests.classroomRequests.CreateClassroomRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.classroomRequests.DeleteClassroomRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.classroomRequests.UpdateClassroomRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.classroomResponses.GetAllClassroomResponse;
import com.estu.StudentManagementSystemDemo.dataAccess.ClassroomRepository;
import com.estu.StudentManagementSystemDemo.dataAccess.StudentRepository;
import com.estu.StudentManagementSystemDemo.dataAccess.TeacherRepository;
import com.estu.StudentManagementSystemDemo.entities.Classroom;
import com.estu.StudentManagementSystemDemo.entities.Student;
import com.estu.StudentManagementSystemDemo.entities.Teacher;
import com.estu.StudentManagementSystemDemo.utilities.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomManager {
    private final ClassroomRepository classroomRepository;
    private final ModelMapperService modelMapperService;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    public void save(CreateClassroomRequest createClassroomRequest){
        Classroom classroom = this.modelMapperService.forRequest().map(createClassroomRequest,Classroom.class);
        this.classroomRepository.save(classroom);
    }

    public List<GetAllClassroomResponse> getAll(){
        List<Classroom> classrooms = this.classroomRepository.findAll();
        List<GetAllClassroomResponse> responses = classrooms.stream()
                .map(classroom -> this.modelMapperService.forResponse()
                        .map(classroom,GetAllClassroomResponse.class))
                .toList();
        return responses;
    }

    public void delete(DeleteClassroomRequest deleteClassroomRequest){

        Classroom classroom = classroomRepository.findById(deleteClassroomRequest.getId())
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + deleteClassroomRequest.getId()));

        // Remove the classroom from all teachers that reference it
        for (Teacher teacher : classroom.getTeachers()) {
            teacher.getClassrooms().remove(classroom);
            teacherRepository.save(teacher);  // Update the teacher
        }

        List<Student> students = studentRepository.findAllByClassroom(classroom);
        for (Student student : students) {
            student.setClassroom(null);
            studentRepository.save(student);
        }

        // Now it's safe to delete the classroom
        classroomRepository.delete(classroom);
    }

    public void update(int id, UpdateClassroomRequest updateClassroomRequest){
        // Find the Classroom entity from the database using the id
        Classroom classroom = this.classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));

        // Update the name of the Classroom entity
        classroom.setName(updateClassroomRequest.getName());

        // Save the updated Classroom entity back to the database
        this.classroomRepository.save(classroom);
    }








}
