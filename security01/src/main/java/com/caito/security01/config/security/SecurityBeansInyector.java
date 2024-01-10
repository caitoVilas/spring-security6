package com.caito.security01.config.security;

import com.caito.security01.api.exceptions.customs.NotFoundException;
import com.caito.security01.domain.repositories.UserRepository;
import com.caito.security01.utils.cons.UserConst;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityBeansInyector {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return (username) -> {
            return  userRepository.findByUsername(username)
                    .orElseThrow(() -> {
                        return new NotFoundException(UserConst.U_NOT_FOUND);
                    });
        };
    }
}
