package com.expense.management.YenyaSoft.advanceManagement.converter.dto;

import com.expense.management.YenyaSoft.advanceManagement.converter.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String section;
    private UserRole role;
}