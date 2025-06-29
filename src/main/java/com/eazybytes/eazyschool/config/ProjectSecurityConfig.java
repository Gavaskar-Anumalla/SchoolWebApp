package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //permit all paths inside web application
        //1-Approach
        /*http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());*/

        //2-Approach
        /*http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());*/

        //deny all requests inside web application
       /* http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());*/

        //Disable The CSRF Protection in Spring Security
        /*http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/","/home").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());*/

        //Customize the security Configurations
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/","/home").authenticated()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user= User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin= User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);

    }
}
