package com.expense.management.YenyaSoft.securityManagement.jwtService;

import com.expense.management.YenyaSoft.advanceManagement.converter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.expense.management.YenyaSoft.advanceManagement.converter.entity.User domainUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found with this: " + email));

        return new org.springframework.security.core.userdetails.User(
                domainUser.getEmail(),
                domainUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(domainUser.getRole().name()))
        );
    }
}
