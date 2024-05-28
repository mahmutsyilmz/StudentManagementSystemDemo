package com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateParentRequest {

    private String name;

    private String surname;

    private String phoneNumber;

}
