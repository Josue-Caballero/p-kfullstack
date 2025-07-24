
package com.kruger.kdevfull.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {

    @NotBlank(message = "{auth.username.required}")
    @Size(
        min = 4,
        message = "{auth.badcredentials}"
    )
    private String username;

    @NotBlank(message = "{auth.password.required}")
    @Size(
        min = 4,
        message = "{auth.badcredentials}"
    )
    private String password;
    
}
