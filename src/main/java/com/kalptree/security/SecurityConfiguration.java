package com.kalptree.security;

import com.kalptree.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    final String[] PUBLIC_URLS = {"api/v1/register"};
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> {
                    auth.antMatchers(PUBLIC_URLS).permitAll();
                    auth.antMatchers("api/v1/admin/*").hasRole(String.valueOf(Roles.ADMIN));
                    auth.antMatchers("api/v1/mod/*").hasAnyRole(String.valueOf(Roles.MODERATOR), String.valueOf(Roles.ADMIN));
                    auth.antMatchers("api/v1/user/*").hasAnyRole(String.valueOf(Roles.USER), String.valueOf(Roles.MODERATOR), String.valueOf(Roles.ADMIN));
                })
                .httpBasic()
                .and()
                .build();
    }

}
