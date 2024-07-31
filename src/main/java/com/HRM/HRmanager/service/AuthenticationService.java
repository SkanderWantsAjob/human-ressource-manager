package com.HRM.HRmanager.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HRM.HRmanager.info.LoginInfo;
import com.HRM.HRmanager.info.RegisterInfo;
import com.HRM.HRmanager.model.Employee;
import com.HRM.HRmanager.repository.UserRepository;

@Service
public class AuthenticationService {
    // ANSI color codes
    String ANSI_RED = "\u001B[31m";
    String ANSI_RESET = "\u001B[0m";
     
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee signup(RegisterInfo input) {
        Employee user = new Employee();

                user.setFullName(input.getFullName());

                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public Employee authenticate(LoginInfo input) {
       
        // Print an example error message in red
        System.err.println(ANSI_RED + "This is a debug message of authenticate" + ANSI_RESET);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        System.err.println(ANSI_RED + "This is a debug message of authenticate2" + ANSI_RESET);

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}