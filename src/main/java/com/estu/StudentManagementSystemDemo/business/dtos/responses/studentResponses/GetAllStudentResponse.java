package com.estu.StudentManagementSystemDemo.business.dtos.responses.studentResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStudentResponse {
    private int id;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private String studentNumber;

    private String classroomName;

    private String parentName;


}
