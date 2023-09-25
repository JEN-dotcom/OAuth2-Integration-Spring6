package com.unknownkoder;

import java.util.*;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.unknownkoder.model.ApplicationUser;
import com.unknownkoder.model.Role;
import com.unknownkoder.repository.RoleRepository;
import com.unknownkoder.repository.UserRepository;

@SpringBootApplication
public class AuthenticatedBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticatedBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
           roleRepository.save(new Role("USER"));

           Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser("admin", passwordEncoder.encode("password"), roles);

            userRepository.save(admin);

        };
    }
}