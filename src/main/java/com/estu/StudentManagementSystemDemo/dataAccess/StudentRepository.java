package com.estu.StudentManagementSystemDemo.dataAccess;

import com.estu.StudentManagementSystemDemo.entities.Classroom;
import com.estu.StudentManagementSystemDemo.entities.Parent;
import com.estu.StudentManagementSystemDemo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    //find all by classroom
    List<Student> findAllByClassroom(Classroom classroom);

    //find all by parent
    List<Student> findAllByParent(Parent parent);



    //find all by classroom name
    List<Student> findAllByClassroomName(String classroomName);
}
