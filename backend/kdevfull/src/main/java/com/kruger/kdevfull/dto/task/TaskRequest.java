
package com.kruger.kdevfull.dto.task;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kruger.kdevfull.enums.TaskStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequest {

    public interface CreateTask {}
    public interface UpdateTask {}
    
    @Size(min = 4, message = "{task.name.size}")
    @NotBlank(message = "{task.name.required}", groups = { CreateTask.class })
    private String title;

    @NotBlank(message = "{task.description.required}", groups = { CreateTask.class })
    private String description;

    @NotNull(message = "{task.projectid.required}", groups = { CreateTask.class })
    @JsonProperty("project_id")
    private Long projectId;
    
    @Min(value = 0, message = "{task.assigneduser.value}", groups = { CreateTask.class })
    @JsonProperty("assigned_user_id")
    private Long assignedToUserId;

    @JsonProperty("due_date")
    private LocalDate dueDate;
    
    private TaskStatus status;

}
