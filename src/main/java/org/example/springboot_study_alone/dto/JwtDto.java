package org.example.springboot_study_alone.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtDto {
    private String accessToken;
}
