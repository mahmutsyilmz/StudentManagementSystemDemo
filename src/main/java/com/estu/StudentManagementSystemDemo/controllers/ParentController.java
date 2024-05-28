package com.estu.StudentManagementSystemDemo.controllers;

import com.estu.StudentManagementSystemDemo.business.ParentManager;
import com.estu.StudentManagementSystemDemo.business.StudentManager;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests.CreateParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests.DeleteParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.requests.parentRequests.UpdateParentRequest;
import com.estu.StudentManagementSystemDemo.business.dtos.responses.parentResponses.GetAllParentResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parents")
@AllArgsConstructor
public class ParentController {
    private final ParentManager parentManager;
    private final StudentManager studentManager;

    @GetMapping
    public List<GetAllParentResponse> getAll(){
        return this.parentManager.getAll();
    }

    @PostMapping
    public void add(CreateParentRequest parent){
        this.parentManager.createParent(parent);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateParentRequest updateParentRequest){
        this.parentManager.updateParent(id, updateParentRequest);

    }

    @DeleteMapping()
    public void delete(DeleteParentRequest deleteParentRequest){
        this.parentManager.deleteParent(deleteParentRequest);
    }
}
