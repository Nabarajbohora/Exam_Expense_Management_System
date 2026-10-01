package com.expense.management.YenyaSoft.advanceManagement.converter.service;

import com.expense.management.YenyaSoft.advanceManagement.converter.dto.UserRequestDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface  UserService {
    UserResponseDto register(UserRequestDto requestDto);
    UserResponseDto login(UserRequestDto requestDto);


}
