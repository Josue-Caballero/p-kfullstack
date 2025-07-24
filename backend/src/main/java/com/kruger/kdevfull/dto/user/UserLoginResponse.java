
package com.kruger.kdevfull.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {

    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;
    
}
