package com.fpt.job5project.config.jwt;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fpt.job5project.entity.User;

import com.fpt.job5project.repository.UserRepository;

@Configuration
public class ApplicationInitConfig {

    private static final String ACCOUNT_ADMIN = "admin";

    private final PasswordEncoder passwordEncoder;

    ApplicationInitConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName(ACCOUNT_ADMIN).isEmpty()) {
                User user = User.builder()
                        .userName(ACCOUNT_ADMIN)
                        .password(passwordEncoder.encode(ACCOUNT_ADMIN))
                        .role(ACCOUNT_ADMIN)
                        .build();

                userRepository.save(user);
            }
        };
    }
}
