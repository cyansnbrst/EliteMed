package com.cyansnbrst.EliteMed;


import com.cyansnbrst.EliteMed.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home", "/registration", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .and()
                .logout()
                    .logoutUrl("/logout") // URL для выхода из приложения
                    .logoutSuccessUrl("/login") // URL, куда перенаправить после успешного выхода
                    .invalidateHttpSession(true) // инвалидировать сессию после выхода
                    .deleteCookies("JSESSIONID") // удалить cookie после выхода
                    .permitAll()
                    .and()
                .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .and()
                    .invalidSessionUrl("/login");

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}