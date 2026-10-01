package com.expense.management.YenyaSoft.advanceManagement.converter.service;

import com.expense.management.YenyaSoft.advanceManagement.converter.dto.UserRequestDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);

    UserResponseDto login(UserRequestDto requestDto);
}
