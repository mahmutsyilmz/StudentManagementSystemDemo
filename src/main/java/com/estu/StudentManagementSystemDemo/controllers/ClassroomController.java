package com.estu.StudentManagementSystemDemo.controllers;

import com.estu.StudentManagementSystemDemo.business.ClassroomManager;
import com.estu.StudentManagementSystemDemo.business.StudentManager;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.classroomRequests.CreateClassroomRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.classroomRequests.DeleteClassroomRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.classroomRequests.UpdateClassroomRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.classroomResponses.GetAllClassroomResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classrooms")
@AllArgsConstructor
public class ClassroomController {
    private final ClassroomManager classroomManager;
    private final StudentManager studentManager;

    @GetMapping
    public List<GetAllClassroomResponse> getAll(){
        return this.classroomManager.getAll();
    }

    @PostMapping
    public void add(CreateClassroomRequest classroom){
        this.classroomManager.save(classroom);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateClassroomRequest updateClassroomRequest){
        this.classroomManager.update(id, updateClassroomRequest);
    }

    @DeleteMapping()
    public void delete(DeleteClassroomRequest deleteClassroomRequest){
        this.classroomManager.delete(deleteClassroomRequest);
    }


}
