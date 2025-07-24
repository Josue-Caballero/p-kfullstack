package com.kruger.kdevfull.service;

import java.util.List;

import com.kruger.kdevfull.dto.task.TaskRequest;
import com.kruger.kdevfull.dto.task.TaskResponse;

public interface TaskServiceI {
    
    TaskResponse create(TaskRequest request);
    
    List<TaskResponse> findByUser(String username);
    
    List<TaskResponse> findByProject(Long projectId);
    
    TaskResponse update(Long taskId, TaskRequest request);
    
    TaskResponse delete(Long id);

}
