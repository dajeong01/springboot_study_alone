package org.example.springboot_study_alone.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Data
public class PrincipalUser implements UserDetails {
    private Integer userId;
    private String username;
    @JsonIgnore // json으로 변환할때 이거는 제외시킴
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

//    @Override
//    public String getPassword() {
//        return password;
//    }
//    @Override
//    public String getUsername() {
//        return username;
//    } 롬북에서 설정되어있어서 생략가능함
}
