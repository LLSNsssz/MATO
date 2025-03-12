package com.lshzzz.mato.service.users;

import com.lshzzz.mato.exception.CustomException;
import com.lshzzz.mato.exception.ErrorCode;
import com.lshzzz.mato.model.users.Users;
import com.lshzzz.mato.repository.UsersRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 데이터베이스에서 사용자 검색
        Users user = usersRepository.findByUserIdAndDeletedAtIsNull(email)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // UserDetails 객체 생성 및 반환
        return new org.springframework.security.core.userdetails.User(
            user.getUserId(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority(user.getRole().name())) // 권한 설정
        );
    }
}