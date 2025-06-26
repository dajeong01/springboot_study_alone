package org.example.springboot_study_alone.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot_study_alone.dto.JwtDto;
import org.example.springboot_study_alone.dto.LoginDto;
import org.example.springboot_study_alone.dto.UserRegisterDto;
import org.example.springboot_study_alone.security.service.JwtService;
import org.example.springboot_study_alone.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

public class UsersController {
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

    @Slf4j
    @RestController
    @RequestMapping("/api/users")
    @RequiredArgsConstructor
    public class UsersController {

        private final UsersService usersService;
        private final JwtService jwtService;
        private LoginDto dto;

        @PostMapping
        public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto dto) {
            log.info("DTO: {}", dto);
            return ResponseEntity.ok(usersService.register(dto));
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginDto dto) {
            JwtDto jwtDto = usersService.login(dto);
            System.out.println("로그인 컨트롤러 호출");
            return ResponseEntity.ok(jwtDto);
        }

        @GetMapping("/login/status")
        public ResponseEntity<?> getLoginStatus(@RequestHeader("Authorization") String authorization) {
            System.out.println(authorization);
            return ResponseEntity.ok(jwtService.validLoginAccessToken(authorization));
        }

        @GetMapping("/principal")
        public ResponseEntity<?> getPrincipalUser() {
            return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
        }
    }

}
