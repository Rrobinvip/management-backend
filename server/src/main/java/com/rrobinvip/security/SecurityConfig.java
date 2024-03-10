package com.rrobinvip.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the Spring Security filter chain to permit all incoming requests without requiring authentication,
     * enable CORS, and disable CSRF protection. This setup is useful for development environments or applications
     * that do not require strict security measures. It allows for easier integration with tools like Swagger UI for API testing.
     *
     * @param http The HttpSecurity instance to be configured.
     * @return The SecurityFilterChain built from the HttpSecurity configuration.
     * @throws Exception if an error occurs during the configuration.
     */
    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                // Enable CORS with default settings
//                .cors().configurationSource(corsConfigurationSource()).and()
//                // Permit all requests without authentication
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests.anyRequest().permitAll()
//                )
//                // Disable CSRF protection
//                .csrf().disable();
//
//        return http.build();
//    }
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Enable CORS with default settings
                .cors().configurationSource(corsConfigurationSource()).and()
                // Configure security for specific paths
                .authorizeRequests(authorizeRequests ->
                                authorizeRequests
//                                // Exclude admin/employee/login from security filters
//                                .requestMatchers("/admin/employee/login").permitAll()
//                                // Apply security restrictions to other paths under admin/**
//                                .requestMatchers("/admin/**").authenticated() // Or any other access control you deem appropriate
//                                // Any other request that doesn't match the above patterns
                                        .anyRequest().permitAll()
                )
                // Disable CSRF protection
                .csrf().disable();

        return http.build();
    }


    /**
     * Defines a CORS configuration source that allows requests from any origin, with any method, and allows headers
     * such as authorization, content-type, and x-auth-token. This broad configuration is suited for development and
     * should be tightened for production environments.
     *
     * @return A CorsConfigurationSource that defines the CORS policy.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Allow all origins for development
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
