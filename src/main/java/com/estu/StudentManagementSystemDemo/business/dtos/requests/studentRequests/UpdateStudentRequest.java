package com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String studentNumber;
    private String classroomName;
}
