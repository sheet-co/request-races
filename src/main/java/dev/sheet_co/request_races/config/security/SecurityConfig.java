package dev.sheet_co.request_races.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
        .requestMatchers(HttpMethod.GET, "/api/hello").permitAll()
        .requestMatchers(HttpMethod.POST, "/api").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/race").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/race").permitAll()
        .requestMatchers(HttpMethod.PUT, "/api/race/put/*").permitAll()
        .requestMatchers(HttpMethod.DELETE, "/api/race/delete/*").permitAll()
        .anyRequest().authenticated());
    return http.build();
  }

}
