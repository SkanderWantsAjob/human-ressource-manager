package com.HRM.HRmanager;



import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.HRM.HRmanager.filter.JwtAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

       private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
        JwtAuthenticationFilter jwtAuthenticationFilter,
        AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(crsf ->crsf.disable())
                .authorizeHttpRequests(registry -> {
                registry.requestMatchers("/auth/**")
                .permitAll();
                registry.requestMatchers("auth/login").permitAll();

                registry.requestMatchers("/api/employees").permitAll();
                registry.requestMatchers("/api/**").permitAll()
                .anyRequest()
                .authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
  
    //  @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
    // AuthenticationEventPublisher publisher
    // ) throws Exception {
    //     {
    //         httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
    //                     .authenticationEventPublisher(publisher);
    //     }
    //     return httpSecurity
    //             .authorizeHttpRequests(registry -> {
    //                 registry.requestMatchers("/home", "/api/test").permitAll();
    //                 registry.requestMatchers("/admin/**").hasRole("ADMIN");
    //                 registry.requestMatchers("/user/**").hasRole("USER");
    //                 registry.anyRequest().authenticated();
    //             })
    //             .formLogin(withDefaults())
    //             .addFilterBefore(new RobotFilter(), UsernamePasswordAuthenticationFilter.class )
                
    //             .build();
    // }


    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    //     UserDetails user = User.builder()
    //     .username("user1")
    //     .password(passwordEncoder.encode("password1"))
    //     .roles("USER")
    //     .build();
    // System.out.println("The password is: " + user.getPassword() + " and username " + user.getUsername());

    //     UserDetails adminUser = User.builder()
    //            .username("admin")
    //            .password(passwordEncoder.encode("pepe"))
    //            .roles("ADMIN", "USER")
    //            .build();
    //    return new InMemoryUserDetailsManager(user, adminUser);

        
    // }   

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public ApplicationListener<AuthenticationSuccessEvent> successListener() {
    //      return event -> {
    //         System.out.println(String.format("SUCCESS [%s] %s", event.getAuthentication().getClass().getName(), event.getAuthentication().getName()));
    //      };
    // }


}