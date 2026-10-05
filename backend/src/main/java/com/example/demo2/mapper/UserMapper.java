package com.example.demo2.mapper;

import com.example.demo2.model.dto.AuthRequest;
import com.example.demo2.model.dto.AuthResponse;
import com.example.demo2.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(AuthRequest request);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "role", target = "role")
    AuthResponse toResponse(User user);
}