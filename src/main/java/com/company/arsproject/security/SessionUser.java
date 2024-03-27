package com.company.arsproject.security;

import com.company.arsproject.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SessionUser {

    public SecurityUser getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser sc) {
            return sc;
        }
        return null;
    }

    public User getUser() {
        return getAuthUser().getUser();
    }

    public Long id() {
        SecurityUser user = getAuthUser();
        if (Objects.isNull(user)) {
            return -1L;
        }
        return user.getUser().getId();
    }
}
