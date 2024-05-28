package com.estu.StudentManagementSystemDemo.business.dtos.responses.parentResponses;

import com.estu.StudentManagementSystemDemo.entities.Parent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class GetAllParentResponse {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private List<String> studentNames;

    public GetAllParentResponse(Parent parent) {
        this.id = parent.getId();
        this.name = parent.getName();
        this.surname = parent.getSurname();
        this.phoneNumber = parent.getPhoneNumber();
        this.studentNames = parent.getStudents().stream().map(student -> student.getName() + " " + student.getSurname()).toList();
    }
}
