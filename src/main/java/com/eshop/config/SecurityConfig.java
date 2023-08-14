package com.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/api/products/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/products/")).hasRole("ADMIN") // POST to add products
                .requestMatchers(new AntPathRequestMatcher("/api/products/{\\d+}")).hasRole("ADMIN") // PUT and DELETE for specific product
                .requestMatchers(new AntPathRequestMatcher("/api/users/register"), new AntPathRequestMatcher("/api/users/login")).permitAll()
                .anyRequest().authenticated()
            .and()
            .csrf().disable() // You might want to disable CSRF protection if your app is stateless or you handle CSRF in another way
            .formLogin().disable() // Disabling the default form login
            .httpBasic().disable(); // Disabling HTTP Basic to prevent browser popup (if not needed)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
