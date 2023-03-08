package com.example.BankApplication.Security;

import com.example.BankApplication.Client.AccountClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@Configuration
public class SecurityConfigue {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity http){
        return http.authorizeExchange()
                .pathMatchers("/tran/**")
                .hasRole("USER")
                .anyExchange().permitAll()
                .and().logout()
                .and().formLogin()
                .and()
                .csrf().disable()
                .build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(AccountClient accountService) {
        UserDetailsRepositoryReactiveAuthenticationManager manager = new UserDetailsRepositoryReactiveAuthenticationManager(accountService);
        manager.setPasswordEncoder(bCryptPasswordEncoder());
        return manager;
    }
}
