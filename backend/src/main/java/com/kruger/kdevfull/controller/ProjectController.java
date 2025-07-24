
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
import org.springframework.web.bind.annotation.RestController;

import com.kruger.kdevfull.dto.project.ProjectRequest;
import com.kruger.kdevfull.dto.project.ProjectResponse;
import com.kruger.kdevfull.dto.project.ProjectRequest.CreateProject;
import com.kruger.kdevfull.dto.project.ProjectRequest.UpdateProject;
import com.kruger.kdevfull.service.ProjectServiceI;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceI projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
        @RequestBody @Validated(value = { CreateProject.class })ProjectRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(projectService.create(request));
        
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAll() {
        
        return ResponseEntity
            .ok(projectService.findAll());
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(
        @PathVariable Long id,
        @RequestBody @Validated(value = { UpdateProject.class }) ProjectRequest request) {

        return ResponseEntity.ok(projectService.update(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectResponse> delete(@PathVariable Long id) {

        return ResponseEntity.ok(projectService.delete(id));

    }

}
