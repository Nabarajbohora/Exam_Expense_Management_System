package com.expense.management.YenyaSoft.serviceimpl;

import com.expense.management.YenyaSoft.advanceManagement.converter.dto.UserRequestDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.dto.UserResponseDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.entity.User;
import com.expense.management.YenyaSoft.advanceManagement.converter.repository.UserRepository;
import com.expense.management.YenyaSoft.advanceManagement.converter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        if (userRepo.existsByUsername(requestDto.getUsername())) {
            throw new RuntimeException("Username name is already exist");
        }
        if (userRepo.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("email is already exists");
        }
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setSection(requestDto.getSection());
        user.setRole(requestDto.getRole());

        User savedUser = userRepo.save(user);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .section(savedUser.getSection())
                .role(savedUser.getRole())
                .build();
    }
}
