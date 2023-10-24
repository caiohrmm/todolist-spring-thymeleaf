package br.com.forgo.todolistforgo.config;

import br.com.forgo.todolistforgo.service.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService service;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
        http.
                csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((httpRequests) -> {
                    httpRequests.requestMatchers("/", "register")
                            .permitAll()
                            .requestMatchers(HttpMethod.POST,
                                    "/api/v1/users/*")
                            .permitAll()
                            .requestMatchers(HttpMethod.POST,
                                    "/api/v1/tasks/*")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                }).formLogin((formLogin) -> {
                    formLogin.loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/profile")
                            .permitAll();
                }).logout((logout) -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .permitAll();
                });

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(service);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception{
        AuthenticationManagerBuilder auth =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(daoAuthenticationProvider());
        return auth.build();
    }
}