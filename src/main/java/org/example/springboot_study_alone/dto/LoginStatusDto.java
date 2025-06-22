package org.example.springboot_study_alone.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class LoginStatusDto {
    private String status;
    private boolean isLogin;
    private Integer userId;
}
