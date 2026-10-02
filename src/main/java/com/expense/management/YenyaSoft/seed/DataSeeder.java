package com.expense.management.YenyaSoft.seed;

import com.expense.management.YenyaSoft.advanceManagement.converter.entity.User;
import com.expense.management.YenyaSoft.advanceManagement.converter.enums.Role;
import com.expense.management.YenyaSoft.advanceManagement.converter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String...args) {
        if (!userRepository.existsByEmail("xettrynabraj377@gmail.com")) {
            userRepository.save(
                    User.builder()
                            .username("account")
                            .email("xettrynabraj377@gmail.com")
                            .password(passwordEncoder.encode("Account@123"))
                            .section("ACCOUNT_SECTION")
                            .role(Role.ACCOUNT_USER)
                            .build()
            );
            System.out.println("Default account user seeded successfully.");
        }
    }
}
