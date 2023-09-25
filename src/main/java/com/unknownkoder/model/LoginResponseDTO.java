package com.unknownkoder.model;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwt;

}
