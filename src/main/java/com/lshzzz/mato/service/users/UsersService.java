package com.lshzzz.mato.service.users;

import com.lshzzz.mato.exception.CustomException;
import com.lshzzz.mato.exception.ErrorCode;
import com.lshzzz.mato.model.users.Role;
import com.lshzzz.mato.model.users.Users;
import com.lshzzz.mato.model.users.dto.UsersLoginResponse;
import com.lshzzz.mato.model.users.dto.UsersRegisterRequest;
import com.lshzzz.mato.model.users.dto.UsersRegisterResponse;
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

    // 회원 가입 로직
    @Transactional
    public UsersRegisterResponse register(UsersRegisterRequest request) {
        // 이메일 중복 확인
        if (usersRepository.existsByUserId(request.userId())) {
            throw new CustomException(ErrorCode.ID_ALREADY_EXISTS);
        }

        // 비밀번호 & 비밀번호 확인 검증
        if (!request.password().equals(request.confirmPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD_CONFIRMATION);
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(request.password());

        // 사용자 저장
        Users user = Users.builder()
            .userId(request.userId())
            .password(encryptedPassword)
            .nickname(request.nickname())
            .role(Role.USER)
            .build();
        usersRepository.save(user);

        return new UsersRegisterResponse(user.getId(), user.getUserId(), user.getNickname(),
            user.getRole());
    }
}