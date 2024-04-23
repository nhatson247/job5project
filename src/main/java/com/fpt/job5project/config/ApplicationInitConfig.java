package com.fpt.job5project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fpt.job5project.entity.User;

import com.fpt.job5project.repository.UserRepository;

@Configuration
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("ADMIN").isEmpty()) {
                User user = User.builder()
                        .userName("ADMIN")
                        .password(passwordEncoder.encode("ADMIN"))
                        .role("ADMIN")
                        .build();

                userRepository.save(user);
            }
        };
    }
}
