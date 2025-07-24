
package com.kruger.kdevfull.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kruger.kdevfull.dto.task.TaskRequest;
import com.kruger.kdevfull.dto.task.TaskResponse;
import com.kruger.kdevfull.enums.State;
import com.kruger.kdevfull.enums.TaskStatus;
import com.kruger.kdevfull.mapper.TaskMapper;
import com.kruger.kdevfull.models.Project;
import com.kruger.kdevfull.models.Task;
import com.kruger.kdevfull.models.User;
import com.kruger.kdevfull.repository.ProjectRepository;
import com.kruger.kdevfull.repository.TaskRepository;
import com.kruger.kdevfull.repository.UserRepository;
import com.kruger.kdevfull.service.TaskServiceI;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskServiceI {

    @Value("${app.projectnotfoud}")
    private String ProjectNotFoundMessage; 

    @Value("${app.usernotfoud}")
    private String UserNotFoundMessage;

    @Value("${app.tasknotfoud}")
    private String TaskNotFoundMessage;

    private final TaskRepository taskRepository;
    
    private final ProjectRepository projectRepo;
    
    private final UserRepository userRepo;
    
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse create(TaskRequest request) {
        
        Project project = projectRepo.findById(request.getProjectId())
            .orElseThrow(() -> new EntityNotFoundException(ProjectNotFoundMessage));
        
        Task task = taskMapper.toEntity(request);
        task.setProject(project);
        task.setStatus(request.getStatus() != null ? request.getStatus() : TaskStatus.PENDING);

        if (request.getAssignedToUserId() != null) {

            User assigned = userRepo.findById(request.getAssignedToUserId())
                .orElseThrow(() -> new EntityNotFoundException(UserNotFoundMessage));
            
            task.setAssignedTo(assigned);
            
        }

        return taskMapper.toResponse(taskRepository.save(task));
    
    }

    @Override
    public List<TaskResponse> findByUser(String username) {
        
        return taskMapper.toResponseList(taskRepository.findByAssignedToUsername(username));

    }

    @Override
    public List<TaskResponse> findByProject(Long projectId) {
        
        Project project = projectRepo.findById(projectId)
            .orElseThrow(() -> new EntityNotFoundException(ProjectNotFoundMessage));

        return taskMapper.toResponseList(taskRepository.findByProjectId(projectId));

    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(TaskNotFoundMessage));

        task.setTitle(request.getTitle() != null ? request.getTitle() : task.getTitle());
        task.setDescription(request.getDescription() != null ? request.getDescription() : task.getDescription());
        task.setDueDate(request.getDueDate() != null ? request.getDueDate() : task.getDueDate());
        task.setStatus(request.getStatus() != null ? request.getStatus() : task.getStatus());

        if (request.getAssignedToUserId() != null) {

            User assigned = userRepo.findById(request.getAssignedToUserId())
                .orElseThrow(() -> new EntityNotFoundException(UserNotFoundMessage));
            
            task.setAssignedTo(assigned);
            
        }
        
        if (request.getProjectId() != null) {

            Project project = projectRepo.findById(request.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException(ProjectNotFoundMessage));
            
            task.setProject(project);
            
        }

        return taskMapper.toResponse(taskRepository.save(task));

    }

    @Override
    public TaskResponse delete(Long id) {
        
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(TaskNotFoundMessage));

        task.setState(State.DELETED);
        
        return taskMapper.toResponse(taskRepository.save(task));
    
    }

}
