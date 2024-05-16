package com.fpt.job5project.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

     private static final String[] publicEndpoints = {
     "api/auth/login", "api/auth/register", "api/auth/logout",
     "api/auth/refresh", "api/auth/introspect",
             "api/province", "api/industry","api/job/getTopJobForHome/{numJobs}",
             "api/v1/files/{fileName}","api/job/{id}","api/employer/{id}","api/jobrequirement/{id}"
             ,"api/job/{id}","api/jobbenefit/{id}","api/jobdecription/{id}",
             "api/jobsIndustries/{id}",
             "api/employer","favicon.ico","api/vnpay/vnpay-payment","api/candidate/update/{id}",
     };

//    private static final String[] publicEndpoints = { "/**"
//    };

    private final CustomJwtDecoder customJwtDecoder;

    SecurityConfig(CustomJwtDecoder customJwtDecoder) {
        this.customJwtDecoder = customJwtDecoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                 request -> request.requestMatchers(HttpMethod.POST,
                 publicEndpoints).permitAll()
                .requestMatchers(HttpMethod.GET,
                        publicEndpoints).permitAll()
                 .anyRequest().authenticated());

                // // truy cập tất cả phương thức
//                request -> request.requestMatchers(publicEndpoints).permitAll()
//                        .anyRequest().authenticated());

        // Support Token
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder)
                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }
}
