package com.estu.StudentManagementSystemDemo.dataAccess;

import com.estu.StudentManagementSystemDemo.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
    //find by name
    Classroom findByName(String name);

    //find by id

    List<Classroom> findAllByNameIn(List<String> classroomNames);

}
