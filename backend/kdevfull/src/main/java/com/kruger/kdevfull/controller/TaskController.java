
package com.kruger.kdevfull.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.kdevfull.dto.task.TaskRequest;
import com.kruger.kdevfull.dto.task.TaskResponse;
import com.kruger.kdevfull.dto.task.TaskRequest.CreateTask;
import com.kruger.kdevfull.dto.task.TaskRequest.UpdateTask;
import com.kruger.kdevfull.service.TaskServiceI;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceI taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(
        @RequestBody @Validated(value = { CreateTask.class }) TaskRequest request) {
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(taskService.create(request));
    
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getByUser(@RequestParam String username) {
        
        return ResponseEntity.ok(taskService.findByUser(username));
    
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> getByProject(@PathVariable Long projectId) {
        
        return ResponseEntity.ok(taskService.findByProject(projectId));
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, 
        @RequestBody @Validated(value = { UpdateTask.class }) TaskRequest request) {
        
        return ResponseEntity.ok(taskService.update(id, request));
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> delete(@PathVariable Long id) {

        return ResponseEntity.ok(taskService.delete(id));
    
    }

}
