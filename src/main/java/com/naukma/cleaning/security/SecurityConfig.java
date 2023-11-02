package com.naukma.cleaning.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        config -> config
                                .requestMatchers(antMatcher(HttpMethod.PUT,"/addresses")).hasAnyRole("User")
                                .requestMatchers(antMatcher(HttpMethod.POST,"/addresses/")).hasAnyRole("User")
                                .requestMatchers(antMatcher(HttpMethod.GET, "/commercial-proposals/**")).permitAll()
                                .requestMatchers(antMatcher("/addresses/**")).permitAll()
//                        .requestMatchers(HttpMethod.GET,"/commercial-proposals/**").hasRole("Employee")
                                .requestMatchers(antMatcher("/swagger-ui/**")
                                        , antMatcher("/v2/api-docs")
                                        , antMatcher("/swagger-resources")
                                        , antMatcher("/swagger-resources/**")
                                        , antMatcher("/configuration/ui")
                                        , antMatcher("/configuration/security")
                                        , antMatcher("/swagger-ui.html")
                                        , antMatcher("/webjars/**")
                                        , antMatcher("/v3/api-docs/**")).hasRole("Admin")
                                .requestMatchers(antMatcher("/h2-console/**")).hasRole("Admin")
                        //  .requestMatchers(antMatcher(HttpMethod.DELETE,"/commercial-proposals/**")).permitAll()
                );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
