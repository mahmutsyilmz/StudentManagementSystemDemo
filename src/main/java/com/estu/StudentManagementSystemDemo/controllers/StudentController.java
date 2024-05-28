package com.estu.StudentManagementSystemDemo.controllers;



import com.estu.StudentManagementSystemDemo.business.ClassroomManager;
import com.estu.StudentManagementSystemDemo.business.StudentManager;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests.CreateStudentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests.UpdateStudentParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.studentRequests.UpdateStudentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.studentResponses.GetAllStudentResponse;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.studentResponses.GetAllStudentsByClassroom;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentManager studentManager;


    @GetMapping("/getAll")
    public List<GetAllStudentResponse> getAll(){
        return this.studentManager.getAll();
    }

    @PostMapping
    public void add(CreateStudentRequest student){
        this.studentManager.save(student);
    }

    @PutMapping("/updateParent")
    public void updateParent(@RequestBody UpdateStudentParentRequest updateStudentParentRequest) {
        this.studentManager.updateParent(updateStudentParentRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.studentManager.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateStudentRequest updateStudentRequest){
        this.studentManager.updateStudent(id, updateStudentRequest);
    }

    @GetMapping("/classroom/{classroomId}")
    public List<GetAllStudentsByClassroom> getAllStudentsByClassroom(@PathVariable int classroomId){
        return this.studentManager.getAllStudentsByClassroom(classroomId);
    }






}
