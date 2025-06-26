package org.example.springboot_study_alone.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_study_alone.domain.entity.User;
import org.example.springboot_study_alone.dto.JwtDto;
import org.example.springboot_study_alone.dto.LoginDto;
import org.example.springboot_study_alone.repository.UsersRepository;
import org.example.springboot_study_alone.security.jwt.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;

    public User register(org.example.springboot_study_alone.dto.UserRegisterDto dto) {
        User insertedUser = usersRepository.save(dto.toEntity(passwordEncoder));
        return insertedUser;
    }

    public JwtDto login(LoginDto dto) {
        List<User> foundUsers = usersRepository.findByUsername(dto.getUsername());
        if(foundUsers.isEmpty()){
            throw new UsernameNotFoundException("사용자 정보(아이디를 못 찾음)를 확인하세요.");
            // error 401
            // 여기서 예외 터지면 바로 login 메서드 종료
        }
        User user = foundUsers.get(0);
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new BadCredentialsException("사용자 정보(비밀번호 오류)를 확인하세요");
        }
        System.out.println("로그인 성공 토큰 생성");
        String token = jwtUtil.generateAccessToken(user.getId().toString());
        return JwtDto.builder().accessToken(token).build();
    }
}

