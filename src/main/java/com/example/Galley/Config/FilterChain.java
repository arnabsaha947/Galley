package com.example.Galley.Config;

import com.example.Galley.Utility.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class FilterChain {

    @Autowired
    private AuthenticationEntryPoint entryPoint;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(x-> x.disable())
                .authorizeHttpRequests(
                        x-> x.requestMatchers("/healthcheck").authenticated().
                                requestMatchers("/login")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(x-> x.authenticationEntryPoint(entryPoint))
                .sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        /*
        Every time a request hits it first checks if authentication is required or not.
         */
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
