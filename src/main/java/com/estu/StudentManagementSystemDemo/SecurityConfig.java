package com.estu.StudentManagementSystemDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails teacher = User.withDefaultPasswordEncoder()
                .username("teacher")
                .password("password")
                .roles("TEACHER")
                .build();

        UserDetails student = User.withDefaultPasswordEncoder()
                .username("student")
                .password("password")
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(teacher, student);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/swagger-resources/**").permitAll()
                .antMatchers("/api/v1/students/getAll").hasRole("STUDENT")
                .antMatchers("/**").hasRole("TEACHER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/swagger-ui.html", true)
                .and()
                .httpBasic();
    }
}
