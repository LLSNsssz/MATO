package com.lshzzz.mato.service.users;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final StringRedisTemplate redisTemplate;

    // Refresh Token 저장
    public void saveRefreshToken(String username, String refreshToken, long duration) {
        redisTemplate.opsForValue()
            .set("refresh:" + username, refreshToken, duration, TimeUnit.SECONDS);
    }

    // Refresh Token 조회
    public String getRefreshToken(String username) {
        return redisTemplate.opsForValue().get("refresh:" + username);
    }

    // Refresh Token 삭제
    public void deleteRefreshToken(String username) {
        redisTemplate.delete("refresh:" + username);
    }
}