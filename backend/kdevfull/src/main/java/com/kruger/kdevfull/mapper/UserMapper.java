
package com.kruger.kdevfull.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.kruger.kdevfull.dto.user.UserRequest;
import com.kruger.kdevfull.dto.user.UserResponse;
import com.kruger.kdevfull.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User toEntity(UserRequest dto);

    UserResponse toResponse(User entity);

    List<UserResponse> toResponseList(List<User> users);

}
