package com.estu.StudentManagementSystemDemo.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name="parent")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    //bir velinin birden fazla öğrencisi olabilir
    @OneToMany(mappedBy = "parent")
    private List<Student> students;



}
