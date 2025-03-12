package com.lshzzz.mato.model.users;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum Role {
    USER("유저");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    // 권한 리스트 반환
    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}