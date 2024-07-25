package com.greenerself.sustainability.config.security;

import com.greenerself.sustainability.auth.filter.JwtRequestFilter;
import com.greenerself.sustainability.userlanding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.greenerself.sustainability.constants.ApplicationConstants.ControllerEndpoints.AUTH;
import static com.greenerself.sustainability.constants.ApplicationConstants.ControllerEndpoints.MAIN;
import static com.greenerself.sustainability.constants.ApplicationConstants.SecurityEndpoints.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    @Lazy
    UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
//                                .requestMatchers(AUTH+"/**", MAIN+AUTH+"/**")
                                .anyRequest()
                                .permitAll()
//                                .anyRequest().authenticated()
                );
//                .authenticationProvider(authenticationProvider())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .formLogin(formLogin ->
//                formLogin
//                        .loginPage(AUTH+LOGIN)
//                        .defaultSuccessUrl(HOME, true)
//                        .failureUrl(AUTH+LOGIN+"?error=true")
//                        .permitAll()
//        )
//                .logout(logout ->
//                        logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher(AUTH+LOGOUT))
//                                .logoutSuccessUrl(AUTH+LOGIN+"?logout=true")
//                                .permitAll()
//                );

        return http.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
