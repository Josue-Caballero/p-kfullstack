
package com.kruger.kdevfull.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectUpdateRequest {
    
    @NotBlank
    private String name;

    private String description;

}
