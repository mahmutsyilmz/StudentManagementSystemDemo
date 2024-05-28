package com.estu.StudentManagementSystemDemo.business;

import com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests.CreateStudentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests.UpdateStudentParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests.UpdateStudentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.studentResponses.GetAllStudentResponse;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.studentResponses.GetAllStudentsByClassroom;
import com.estu.StudentManagementSystemDemo.dataAccess.ClassroomRepository;
import com.estu.StudentManagementSystemDemo.dataAccess.ParentRepository;
import com.estu.StudentManagementSystemDemo.dataAccess.StudentRepository;
import com.estu.StudentManagementSystemDemo.entities.Classroom;
import com.estu.StudentManagementSystemDemo.entities.Parent;
import com.estu.StudentManagementSystemDemo.entities.Student;
import com.estu.StudentManagementSystemDemo.utilities.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentManager{
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    private final ModelMapperService modelMapperService;
    private final ParentRepository parentRepository;


    public List<GetAllStudentResponse> getAll(){
        List<Student> students = studentRepository.findAll();
        List<GetAllStudentResponse> responses = students.stream()
                .map(student -> this.modelMapperService.forResponse()
                        .map(student, GetAllStudentResponse.class))
                .toList();

        return responses;
    }

    public List<GetAllStudentsByClassroom> getAllStudentsByClassroom(int classroomId){
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + classroomId));

        List<Student> students = this.studentRepository.findAllByClassroom(classroom);
        List<GetAllStudentsByClassroom> responses = students.stream()
                .map(student -> this.modelMapperService.forResponse()
                        .map(student, GetAllStudentsByClassroom.class))
                .toList();

        return responses;

    }


    public void save(CreateStudentRequest createStudentRequest){
        // CreateStudentRequest nesnesini Student nesnesine dönüştür
        Student student = this.modelMapperService.forRequest().map(createStudentRequest,Student.class);

        // createStudentRequest nesnesindeki classroomName alanını kullanarak ilgili Classroom nesnesini bul
        Classroom classroom = this.classroomRepository.findByName(createStudentRequest.getClassroomName());

        if (classroom == null) {
            throw new RuntimeException("Classroom not found with name: " + createStudentRequest.getClassroomName());
        }

        // Bulunan Classroom nesnesini Student nesnesinin classroom alanına ata
        student.setClassroom(classroom);

        // Student nesnesini veritabanına kaydet
        this.studentRepository.save(student);
    }

    public void updateParent (UpdateStudentParentRequest updateStudentParentRequest){
        // Find the Student entity from the database using the id
        Student student = this.studentRepository.findById(updateStudentParentRequest.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + updateStudentParentRequest.getStudentId()));

        // Find the Parent entity from the database using the id
        Parent parent = this.parentRepository.findById(updateStudentParentRequest.getParentId())
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + updateStudentParentRequest.getParentId()));

        // Update the parent of the Student entity
        student.setParent(parent);

        // Save the updated Student entity back to the database
        this.studentRepository.save(student);

    }

    public void delete(int id){
        // Find the Student entity from the database using the id
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Remove the student from the parent
        Parent parent = student.getParent();
        if (parent != null) {
            parent.getStudents().remove(student);
            parentRepository.save(parent);
        }

        // Now it's safe to delete the student
        studentRepository.delete(student);
    }

    public void updateStudent(int id, UpdateStudentRequest updateStudentRequest){
        // Find the existing student
        Student existingStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Find the Classroom entity from the database
        Classroom classroom = this.classroomRepository.findByName(updateStudentRequest.getClassroomName());

        // Map the UpdateStudentRequest to the existing Student entity
        this.modelMapperService.forRequest().map(updateStudentRequest, existingStudent);

        // Set the found Classroom entity to the Student entity
        existingStudent.setClassroom(classroom);

        // Save the updated Student entity back to the database
        this.studentRepository.save(existingStudent);
    }












}
