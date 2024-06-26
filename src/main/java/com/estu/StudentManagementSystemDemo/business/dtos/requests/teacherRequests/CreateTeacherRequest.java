package com.estu.StudentManagementSystemDemo.business.dtos.requests.teacherRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeacherRequest {

        private String name;
        private String surname;
        private String email;
        private String phoneNumber;
        private List<String> classroomNames;

}
