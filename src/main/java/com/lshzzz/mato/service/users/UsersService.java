package com.lshzzz.mato.service.users;

import com.lshzzz.mato.exception.CustomException;
import com.lshzzz.mato.exception.ErrorCode;
import com.lshzzz.mato.model.users.Users;
import com.lshzzz.mato.model.users.dto.UsersLoginResponse;
import com.lshzzz.mato.repository.UsersRepository;
import com.lshzzz.mato.utils.users.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인 로직
    @Transactional
    public UsersLoginResponse login(String userId, String rawPassword) {
        Users user = usersRepository.findByUserIdAndDeletedAtIsNull(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return UsersMapper.toLoginResponse(user);
    }
}