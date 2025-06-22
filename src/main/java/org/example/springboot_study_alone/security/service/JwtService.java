package org.example.springboot_study_alone.security.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.example.springboot_study_alone.dto.LoginStatusDto;
import org.example.springboot_study_alone.exception.BearerValidException;
import org.example.springboot_study_alone.security.jwt.JwtUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;

    public LoginStatusDto validLoginAccessToken(String bearerToken){
        if(!jwtUtil.isBearer(bearerToken)){
            throw new BearerValidException();
        }
        String accessToken = jwtUtil.removeBearer(bearerToken);
        Claims claims = jwtUtil.getClaims(accessToken);
        Integer userId = Integer.parseInt(claims.getId());
        return LoginStatusDto.builder()
                .status("success")
                .isLogin(userId != null)
                .userId(userId)
                .build();
    }
}
