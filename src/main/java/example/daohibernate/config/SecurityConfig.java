package example.daohibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(HttpMethod.GET, "/persons/public")
                .permitAll()
                .anyRequest()
                .authenticated()); // Все остальные требуют авторизации
        http.csrf(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());
        http.sessionManagement(Customizer.withDefaults());
        http.headers(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder user = User.builder();
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(user.username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build());
        userDetailsManager.createUser(user.username("user")
                .password("{noop}user")
                .roles("USER")
                .build());

        return userDetailsManager;
    }
}
