package org.abhishek.many_to_many.config;

import org.abhishek.many_to_many.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/students/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/courses/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/students/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/students/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/students/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/courses/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/courses/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/courses/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(org.springframework.security.config.Customizer.withDefaults());
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
