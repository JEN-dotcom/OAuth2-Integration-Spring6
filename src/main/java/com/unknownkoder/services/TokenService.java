package com.unknownkoder.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    public String generateJwt(Authentication auth) {
        Instant now = Instant.now();
        Instant expirationTime = now.plus(10, ChronoUnit.MINUTES); // Set expiration to 10 minutes from now

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expirationTime)
                .subject(auth.getName())
                .claim("roles", scope)

                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public  void decode(String str) throws Exception {
        // Your JWT token (replace with your actual JWT)
        String jwtToken = str;

        // Parse the JWT
        JWT jwt = JWTParser.parse(jwtToken);

        // Check if it's a signed JWT
        if (jwt instanceof SignedJWT) {
            SignedJWT signedJWT = (SignedJWT) jwt;

            // Access JWT claims
            String subject = signedJWT.getJWTClaimsSet().getSubject();
            String issuer = signedJWT.getJWTClaimsSet().getIssuer();
            // Access other claims as needed
            // ...

            // Perform custom authorization logic based on claims
            if ("admin".equals(subject)) {
                System.out.println("User is an admin.");
                // Custom logic for admin users
            } else {
                System.out.println("User is not an admin.");
                // Custom logic for non-admin users
            }
        } else {
            System.out.println("Invalid JWT format.");
            // Handle invalid JWT format
        }
    }
}
