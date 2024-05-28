package com.estu.StudentManagementSystemDemo.business.dtos.responses.teacherResponses;

import com.estu.StudentManagementSystemDemo.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import com.estu.StudentManagementSystemDemo.entities.Classroom;


@Data
@NoArgsConstructor
public class GetAllTeacherResponse {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private List<String> classroomName;

    public GetAllTeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.surname = teacher.getSurname();
        this.phoneNumber = teacher.getPhoneNumber();
        this.email = teacher.getEmail();
        this.classroomName = teacher.getClassrooms().stream().map(Classroom::getName).collect(Collectors.toList());
    }

}
