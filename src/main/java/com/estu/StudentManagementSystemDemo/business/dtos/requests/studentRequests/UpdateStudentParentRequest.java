package com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentParentRequest {
    private int studentId;
    private int parentId;
}
