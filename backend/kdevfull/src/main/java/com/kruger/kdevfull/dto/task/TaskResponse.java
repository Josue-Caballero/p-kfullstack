
package com.kruger.kdevfull.dto.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kruger.kdevfull.enums.TaskStatus;
import com.kruger.kdevfull.models.Project;
import com.kruger.kdevfull.models.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {
    
    private Long id;
    
    private String title;
    
    private String description;
    
    private TaskStatus status;
    
    @JsonProperty("assigned_user")
    private User assignedTo;
    
    // private Project project;
    
    @JsonProperty("due_date")
    private LocalDate dueDate;
    
    @JsonProperty("create_at")
    private LocalDateTime createdAt;

}
