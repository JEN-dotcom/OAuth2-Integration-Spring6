package com.unknownkoder.controllers;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    
    @GetMapping("/")
    public String helloUserController() {
        return "Admin access";
    }

    /*
    @GetMapping("/private")
    public String privateEndpoint(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        // Access JWT claims and user information
        String username = principal.getName(); // Username from JWT
        String role = jwt.getClaim("admin"); // Custom claim from JWT

        return "Hello, " + username + "! Your role is: " + role;
    }

    */
}
