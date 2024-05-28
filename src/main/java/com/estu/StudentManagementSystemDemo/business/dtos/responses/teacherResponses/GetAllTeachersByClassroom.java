package com.estu.StudentManagementSystemDemo.business.dtos.responses.teacherResponses;

import com.estu.StudentManagementSystemDemo.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTeachersByClassroom {
    private String name;
    private String surname;

    public GetAllTeachersByClassroom(Teacher teacher) {
        this.name = teacher.getName();
        this.surname = teacher.getSurname();
    }
}
