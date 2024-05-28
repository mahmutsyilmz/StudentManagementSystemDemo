package com.estu.StudentManagementSystemDemo.business;


import com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests.CreateParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests.DeleteParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests.UpdateParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.parentResponses.GetAllParentResponse;
import com.estu.StudentManagementSystemDemo.dataAccess.ParentRepository;
import com.estu.StudentManagementSystemDemo.dataAccess.StudentRepository;
import com.estu.StudentManagementSystemDemo.entities.Parent;
import com.estu.StudentManagementSystemDemo.utilities.mappers.ModelMapperService;
import com.estu.StudentManagementSystemDemo.entities.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParentManager {
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final ModelMapperService modelMapperService;

    public void createParent(CreateParentRequest createParentRequest) {
        Parent parent = this.modelMapperService.forRequest().map(createParentRequest, Parent.class);
        this.parentRepository.save(parent);
    }
    public List<GetAllParentResponse> getAll() {
        List<Parent> parents = this.parentRepository.findAll();
        List<GetAllParentResponse> responses = parents.stream()
                .map(GetAllParentResponse::new)
                .toList();
        return responses;
    }

    public void updateParent(int id, UpdateParentRequest updateParentRequest) {
        Parent parent = this.modelMapperService.forRequest().map(updateParentRequest, Parent.class);
        parent.setId(id);
        this.parentRepository.save(parent);
    }

    public void deleteParent(DeleteParentRequest deleteParentRequest) {
        Parent parent = parentRepository.findById(deleteParentRequest.getId())
                .orElseThrow(() -> new RuntimeException("Parent not found with id: " + deleteParentRequest.getId()));

        // Find all students associated with this parent
        List<Student> students = studentRepository.findAllByParent(parent);

        // Set parent to null for all these students
        for (Student student : students) {
            student.setParent(null);
            studentRepository.save(student);
        }

        // Now it's safe to delete the parent
        parentRepository.delete(parent);
    }
}
