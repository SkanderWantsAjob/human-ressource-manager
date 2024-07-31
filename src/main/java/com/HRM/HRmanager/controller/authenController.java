package com.HRM.HRmanager.controller;

import org.springframework.web.bind.annotation.RestController;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class authenController {
    @GetMapping()
    public String Home(   //Authentication authentication (injecting it)
    ) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return "welcome " + authentication.getName();

    }

    
}
