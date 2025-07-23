
package com.kruger.kdevfull.dto.project;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    
    private Long id;
    
    private String name;
    
    private String description;
    
    private LocalDateTime createdAt;

}
