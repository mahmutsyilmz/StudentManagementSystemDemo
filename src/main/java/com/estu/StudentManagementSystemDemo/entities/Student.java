package com.estu.StudentManagementSystemDemo.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String studentNumber;
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
    // Add this field
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

}
