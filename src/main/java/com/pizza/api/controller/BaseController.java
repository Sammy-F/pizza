package com.pizza.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/")
    public String base() {
        return "Welcome to Pizza.com";
    }

    @GetMapping("/api")
    public String welcome() {
        return "Welcome to the Pizza API";
    }

    @GetMapping("/api/v1")
    public String welcomeV1() {
        return "Welcome to Version 1.0 of the Pizza API";
    }
    
}