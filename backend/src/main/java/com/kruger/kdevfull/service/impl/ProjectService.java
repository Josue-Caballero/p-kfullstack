package com.kruger.kdevfull.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kruger.kdevfull.dto.project.ProjectRequest;
import com.kruger.kdevfull.dto.project.ProjectResponse;
import com.kruger.kdevfull.enums.State;
import com.kruger.kdevfull.mapper.ProjectMapper;
import com.kruger.kdevfull.mapper.UserMapper;
import com.kruger.kdevfull.models.Project;
import com.kruger.kdevfull.models.User;
import com.kruger.kdevfull.repository.ProjectRepository;
import com.kruger.kdevfull.repository.UserRepository;
import com.kruger.kdevfull.service.CurrentUserServiceI;
import com.kruger.kdevfull.service.ProjectServiceI;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectServiceI {

    @Value("${app.projectnotfoud}")
    private String ProjectNotFoundMessage;

    @Value("${app.ownernotfoud}")
    private String UserNotFoundMessage;

    private final UserMapper userMapper;

    private final ProjectMapper projectMapper;

    private final UserRepository userRepository;
    
    private final ProjectRepository projectRepository;

    private final CurrentUserServiceI authContext;

    @Override
    public ProjectResponse create(ProjectRequest request) {
        
        User owner = userRepository.findByUsername(authContext.getUsername())
            .orElseThrow(() -> new EntityNotFoundException(UserNotFoundMessage));

        Project project = projectMapper.toEntity(request);
        project.setOwner(owner);
        
        ProjectResponse response = projectMapper.toResponse(projectRepository.save(project));
        response.setOwner(userMapper.toResponse(owner));
        
        return response;

    }

    @Override
    public List<ProjectResponse> findAll() {
        
        List<Project> projects = projectRepository
            .findAllByCreatedByAndStateNot(authContext.getUsername(), State.DELETED);
        
        List<ProjectResponse> response = projectMapper.toResponseList(projects);
        
        for (int i = 0; i < projects.size(); i++) {
            response.get(i).setOwner(userMapper.toResponse(projects.get(i).getOwner()));
        }

        return response;

    }

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {
        
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ProjectNotFoundMessage));

        project.setName(request.getName() != null ? request.getName() : project.getName());
        project.setState(request.getState() != null ? request.getState() : project.getState());
        project.setDescription(request.getDescription() != null ? request.getDescription() 
            : project.getDescription() );
        project.setOwner(
            request.getOwner() == null ? project.getOwner() : userRepository.findById(request.getOwner())
            .orElseThrow(() -> new EntityNotFoundException(UserNotFoundMessage))
        );
        
        return projectMapper.toResponse(projectRepository.save(project));

    }

    @Override
    public ProjectResponse delete(Long id) {
        
        Project project = projectRepository.findByIdAndCreatedBy(id, authContext.getUsername())
            .orElseThrow(() -> new EntityNotFoundException(ProjectNotFoundMessage));

        project.setState(State.DELETED);

        return projectMapper.toResponse(projectRepository.save(project));
        
    }
    
}
