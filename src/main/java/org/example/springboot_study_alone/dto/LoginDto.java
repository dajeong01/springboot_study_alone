package org.example.springboot_study_alone.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginDto {
    private String username;
    private String password;
}
