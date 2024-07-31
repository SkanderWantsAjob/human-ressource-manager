package com.HRM.HRmanager.controller;

import com.HRM.HRmanager.model.Employee;
import com.HRM.HRmanager.info.LoginInfo;
import com.HRM.HRmanager.info.RegisterInfo;
import com.HRM.HRmanager.responses.LoginResponse;
import com.HRM.HRmanager.service.AuthenticationService;
import com.HRM.HRmanager.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
     // ANSI color codes
     String ANSI_RED = "\u001B[31m";
     String ANSI_RESET = "\u001B[0m";
     
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Employee> register(@RequestBody RegisterInfo registerInfo) {
        Employee registeredUser = authenticationService.signup(registerInfo);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginInfo loginInfo) {
        System.err.println(ANSI_RED + "This is an error message of login controller1" + ANSI_RESET);

        Employee authenticatedUser = authenticationService.authenticate(loginInfo);
        System.err.println(ANSI_RED + "This is an error message of controller2" + ANSI_RESET);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        System.err.println(ANSI_RED + "This is an error message of controller3" + ANSI_RESET);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}