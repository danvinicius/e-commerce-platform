package com.danvinicius.ecommerce.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(HttpMethod.POST,
                                        "/api/address",
                                        "/api/order",
                                        "/api/cart")
                                .hasRole("USER")
                                .requestMatchers(HttpMethod.GET,
                                        "/api/address/**",
                                        "/api/order/**",
                                        "/api/cart/**",
                                        "/api/user")
                                .hasRole("USER")
                                .requestMatchers(HttpMethod.PUT,
                                        "/api/address/**",
                                        "/api/order/**",
                                        "/api/cart/**",
                                        "/api/user/*")
                                .hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE,
                                        "/api/address/**",
                                        "/api/order/**",
                                        "/api/cart/**",
                                        "/api/user/*")
                                .hasRole("USER")
                                .requestMatchers(HttpMethod.GET,
                                        "/api/user",
                                        "/api/user/admin/**")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,
                                        "/api/product",
                                        "/api/category",
                                        "/api/user/admin")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,
                                        "/api/product/**",
                                        "/api/category/**",
                                        "/api/user/admin/**")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,
                                        "/api/product/**",
                                        "/api/category/**",
                                        "/api/user/admin/**")
                                .hasRole("ADMIN")
                                .anyRequest().permitAll())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
