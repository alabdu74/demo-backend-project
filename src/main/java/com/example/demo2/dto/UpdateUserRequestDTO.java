package com.example.demo2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {
    @Size(min = 2, max = 50, message = "firstName must be 2-50 characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "lastName must be 2-50 characters")
    private String lastName;

    @Email(message = "email must be valid")
    private String email;

    private Role role;

}
