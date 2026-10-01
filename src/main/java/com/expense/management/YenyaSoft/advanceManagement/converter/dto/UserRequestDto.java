package com.expense.management.YenyaSoft.advanceManagement.converter.dto;

import com.expense.management.YenyaSoft.advanceManagement.converter.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private Long id;
    @NotBlank(message = "username is required ")
    private String username;

    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required ")
    private String password;

    @NotBlank(message = "section is required")
    private String section;

    @NotNull(message = "role is required ")
    private UserRole role;
}
