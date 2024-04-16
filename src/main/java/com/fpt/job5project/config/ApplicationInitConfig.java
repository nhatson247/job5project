package com.fpt.job5project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fpt.job5project.entity.User;
import com.fpt.job5project.enums.Role;
import com.fpt.job5project.repository.UserRepository;

import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("ADMIN").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                User user = User.builder()
                        .userName("ADMIN")
                        .password(passwordEncoder.encode("ADMIN"))
                        .roles(roles)
                        .build();

                userRepository.save(user);
            }
        };
    }
}
