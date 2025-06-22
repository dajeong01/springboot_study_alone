package org.example.springboot_study_alone.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.springboot_study_alone.domain.entity.User;

@Data
@RequiredArgsConstructor
public class UserRegisterDto {

    private String username;
    private String password;
    private String fullName;
    private String email;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .email(email)
                .build();

    }
}

