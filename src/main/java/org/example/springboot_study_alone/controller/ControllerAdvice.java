package org.example.springboot_study_alone.controller;

import io.jsonwebtoken.JwtException;
import org.example.springboot_study_alone.exception.BearerValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<?> unAuthorized(AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }

        @ExceptionHandler(BearerValidException.class)
        public ResponseEntity<?> isNotBearer(BearerValidException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        @ExceptionHandler(JwtException.class)
        public ResponseEntity<?> jwtError(JwtException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

}
