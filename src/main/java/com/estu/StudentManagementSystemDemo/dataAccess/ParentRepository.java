package com.estu.StudentManagementSystemDemo.dataAccess;

import com.estu.StudentManagementSystemDemo.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent,Integer> {
}
