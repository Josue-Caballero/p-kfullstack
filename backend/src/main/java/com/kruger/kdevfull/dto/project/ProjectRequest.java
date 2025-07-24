
package com.kruger.kdevfull.dto.project;

import com.kruger.kdevfull.enums.State;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRequest {
    
    public interface CreateProject {}
    public interface UpdateProject {}

    @Size(min = 4, message = "{project.name.size}", groups = { CreateProject.class })
    @NotBlank(message = "{project.name.required}", groups = { CreateProject.class })
    private String name;

    private String description;
    
    @NotNull(message = "{project.owner.required}", groups = { CreateProject.class })
    @Min(value = 0, message = "{project.owner.value}", groups = { CreateProject.class })
    private Long owner;

    private State state;
    
}
