package com.company.arsproject;

import com.company.arsproject.security.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@EnableAsync
public class ArsProjectApplication {
    private final SessionUser sessionUser;

    public static void main(String[] args) {
        SpringApplication.run(ArsProjectApplication.class, args);
    }

    @Bean
    public AuditorAware<Long> getAuditor() {
        return () -> Optional.of(sessionUser.id());
    }
}
