package com.example.demo2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "firstName is required")
    @Size(min = 2, max = 50, message = "firstName must be 5-50 characters")
    private String firstName;

    @NotBlank(message = "lastName is required")
    @Size(min = 2, max = 50, message = "lastName must be 2-50 characters")
    private String lastName;

    @NotBlank("email is required")
    @Email(message = "email must be valid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 100, message = "password must be 6-50 characters")
    private String password;

    private Role role;

}
