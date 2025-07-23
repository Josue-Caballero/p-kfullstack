
package com.kruger.kdevfull.dto.project;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRequest {
    
    @Size(min = 4, message = "{project.name.size}")
    @NotBlank(message = "{project.name.required}")
    private String name;

    private String description;

    @NotNull(message = "{project.owner.required}")
    @Min(value = 0, message = "{project.owner.value}")
    private Long owner;

}
