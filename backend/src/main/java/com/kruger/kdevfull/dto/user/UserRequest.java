
package com.kruger.kdevfull.dto.user;

import com.kruger.kdevfull.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    
    @NotBlank(message = "{auth.username.required}")
    @Size(min = 4, message = "{auth.username.size}")
    private String username;

    @Email
    @NotBlank(message = "{user.email.required}")
    private String email;

    @NotBlank
    @Size(min = 8, message = "{auth.password.size}")
    private String password;

    @NotNull(message = "{user.role.required}")
    private Role role;

}
