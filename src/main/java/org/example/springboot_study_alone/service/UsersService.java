package org.example.springboot_study_alone.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_study_alone.domain.entity.User;
import org.example.springboot_study_alone.dto.UserRegisterDto;
import org.example.springboot_study_alone.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public User register(UserRegisterDto dto) {
        User insertedUser = usersRepository.save(dto.toEntity(passwordEncoder));
        return insertedUser;

    }
}
