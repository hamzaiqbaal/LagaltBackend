package com.example.lagaltcaseapplication.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().sessionManagement().disable()
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        // Define the mappings
                        // projects/ 200

                       // .antMatchers("/projects/", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                       // .antMatchers("/projects/id").hasRole("Admin")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter());
        return jwtAuthenticationConverter;
    }
    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> keycloakRoleConverter() {
        return new KeycloakRoleConverter();
    }
    public static class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            // Check roles under realm_access
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess != null && !realmAccess.isEmpty()) {
                Collection<String> realmRoles = (Collection<String>) realmAccess.get("roles");
                if (realmRoles != null) {
                    grantedAuthorities.addAll(
                            realmRoles.stream()
                                    .map(role -> "ROLE_" + role.toUpperCase())
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList())
                    );
                }
            }

            // Check roles under resource_access
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            if (resourceAccess != null && !resourceAccess.isEmpty()) {
                for (Map.Entry<String, Object> entry : resourceAccess.entrySet()) {
                    Map<String, Object> resource = (Map<String, Object>) entry.getValue();
                    Collection<String> resourceRoles = (Collection<String>) resource.get("roles");
                    if (resourceRoles != null) {
                        grantedAuthorities.addAll(
                                resourceRoles.stream()
                                        .map(role -> "ROLE_" + role.toUpperCase())
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }
            System.out.println("Granted Authorities: " + grantedAuthorities.toString());


            return grantedAuthorities;
        }
    }}