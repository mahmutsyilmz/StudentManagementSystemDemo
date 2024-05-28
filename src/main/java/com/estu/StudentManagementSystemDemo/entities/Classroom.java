package com.estu.StudentManagementSystemDemo.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Table(name="classrooms")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    // A classroom can have multiple teachers
    @ManyToMany(mappedBy = "classrooms")
    private List<Teacher> teachers;

    // A classroom can have multiple students
    @OneToMany(mappedBy = "classroom")
    private List<Student> students;





}
