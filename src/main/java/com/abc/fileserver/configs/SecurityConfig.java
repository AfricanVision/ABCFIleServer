package com.abc.fileserver.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (evaluate if needed)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/files/upload", "/files/delete/**").authenticated()  // Secure specific endpoints
                        .anyRequest().permitAll()  // Allow all other requests
                )
                .httpBasic(withDefaults());  // Enable HTTP Basic Authentication

        return http.build();
    }
}
