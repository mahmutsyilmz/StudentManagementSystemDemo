package com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateParentRequest {

    private String name;
    private String surname;
    private String phoneNumber;
}
