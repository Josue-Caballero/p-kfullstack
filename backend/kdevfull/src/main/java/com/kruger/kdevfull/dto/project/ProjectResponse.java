
package com.kruger.kdevfull.dto.project;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kruger.kdevfull.dto.user.UserResponse;
import com.kruger.kdevfull.models.Task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    
    private Long id;
    
    private String name;
    
    private UserResponse owner;
    
    private String description;

    @JsonProperty("create_at")
    private LocalDateTime createdAt;

    private List<Task> tasks;

}
