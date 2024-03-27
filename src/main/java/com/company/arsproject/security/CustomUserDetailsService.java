package com.company.arsproject.security;

import com.company.arsproject.entity.User;
import com.company.arsproject.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with '%s' username not found"));
        return new SecurityUser(user);
    }
}
