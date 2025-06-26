package org.example.springboot_study_alone.config;


import lombok.RequiredArgsConstructor;
import org.example.springboot_study_alone.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // React 개발 서버 주소 허용
        corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL);
        // 자바스크립트 fetch, axios 등 요청에서 사용할 수 있는 헤더 허용
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // GET, POST, PUT, DELETE 등 모든 HTTP 메서드 허용
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());               // 위에 만든 cors 설정(bean) securty에 적용
        http.csrf(csrf -> csrf.disable());                  // 서버사이드 렌더링 로그인 방식 비활성화
        http.formLogin(formLogin -> formLogin.disable());   // 서버사이드 렌더링 로그인 방식 비활성화
        http.httpBasic(httpBasic -> httpBasic.disable());   // HTTP 프로토콜 기본 로그인 방식 비활성화
        http.logout(logout -> logout.disable());            // 서버사이드 렌더링 로그아웃 병식 비활성화

//        http.addFilterBefore(studyFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 특정 요청 ul에 대한 권한 설정
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/users","/api/users/login", "/api/users/login/status", "/api/users/principal").permitAll();
            auth.anyRequest().authenticated();
            // 모든 요청과 인증이 필요함
        });

        //  httpSecrity 객체 설정한 모든 정보를 기반으로 build 하여
        // SecurityFilterChain 객체 생성 후 Bean 등록
        return http.build();
    }
}
