package com.security.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/greet")
    public String getGreetings(final HttpServletRequest servletRequest) {
        return "Hello demo for spring security" + servletRequest.getSession().getId();
    }
}
