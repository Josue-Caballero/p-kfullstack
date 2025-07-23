
package com.kruger.kdevfull.dto.user;

import java.time.LocalDateTime;

import com.kruger.kdevfull.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    
    private Long id;

    private String username;

    private String email;

    private Role role;

    private LocalDateTime createdAt;

}
