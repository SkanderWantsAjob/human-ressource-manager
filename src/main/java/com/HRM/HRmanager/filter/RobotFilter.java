package com.HRM.HRmanager.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.HRM.HRmanager.authentication.RobotAuthentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RobotFilter extends OncePerRequestFilter {
    private final String HEADER_NAME = "x-robot-filter";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!Collections.list(request.getHeaderNames()).contains(HEADER_NAME)){
            System.out.println("no robot filter :((");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("ROBOT FILTER");
        // authentication decision
        var password = request.getHeader("x-robot-password");
        if (! ("beep-boop".equals(password))) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-type", "text/plain;charset=utf-8");
            response.getWriter().println("you are not robot 111111111111");
        }
        
    // OK
    var newContext = SecurityContextHolder.createEmptyContext();
    newContext.setAuthentication(new RobotAuthentication());
    SecurityContextHolder.setContext(newContext);
    filterChain.doFilter(request, response); 
    return ;
    }
}