package com.estu.StudentManagementSystemDemo.business.dtos.responses.studentResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllStudentsByClassroom {

    private String name;
    private String surname;
    private String studentNumber;

}
