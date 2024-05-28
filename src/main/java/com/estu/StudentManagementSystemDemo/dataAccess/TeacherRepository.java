package com.estu.StudentManagementSystemDemo.dataAccess;

import com.estu.StudentManagementSystemDemo.entities.Classroom;
import com.estu.StudentManagementSystemDemo.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    //find all by classroom name
    List<Teacher> findAllByClassroomsName(String classroomName);

    //find by classroom
    List<Teacher> findByClassrooms(Classroom classroom);

}
