package com.estu.StudentManagementSystemDemo.controllers;

import com.estu.StudentManagementSystemDemo.business.TeacherManager;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.teacherRequests.CreateTeacherRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.teacherRequests.DeleteTeacherRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.teacherRequests.UpdateTeacherRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.teacherResponses.GetAllTeacherResponse;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.teacherResponses.GetAllTeachersByClassroom;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeacherController {
    private final TeacherManager teacherManager;

    @GetMapping
    public List<GetAllTeacherResponse> getAll(){
        return this.teacherManager.getAll();
    }

    @PostMapping
    public void add(CreateTeacherRequest teacher){
        this.teacherManager.createTeacher(teacher);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateTeacherRequest updateTeacherRequest){
        this.teacherManager.updateTeacher(id, updateTeacherRequest);

    }

    @DeleteMapping()
    public void delete(DeleteTeacherRequest deleteTeacherRequest){
        this.teacherManager.deleteTeacher(deleteTeacherRequest);
    }

    @GetMapping("/classroom/{classroomId}")
    public List<GetAllTeachersByClassroom> getAllTeachersByClassroom(@PathVariable int classroomId){
        return this.teacherManager.getAllTeachersByClassroom(classroomId);
    }




}
