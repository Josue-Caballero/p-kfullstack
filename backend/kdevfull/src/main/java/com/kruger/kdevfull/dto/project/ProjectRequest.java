
package com.kruger.kdevfull.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRequest {
    
    @Size(min = 4, message = "{project.name.size}")
    @NotBlank(message = "{project.name.required}")
    private String name;

    private String description;

}

