package com.lshzzz.mato.controller.users;

import com.lshzzz.mato.model.users.dto.UsersLoginRequest;
import com.lshzzz.mato.model.users.dto.UsersLoginResponse;
import com.lshzzz.mato.service.users.RefreshTokenService;
import com.lshzzz.mato.service.users.UsersService;
import com.lshzzz.mato.utils.users.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<UsersLoginResponse> login(
        @Valid @RequestBody UsersLoginRequest request,
        HttpServletResponse response
    ) {
        UsersLoginResponse loginResponse = usersService.login(request.userId(), request.password());

        String accessToken = jwtUtil.createJwt("access", loginResponse.userId(),
            loginResponse.role().name(), 600000L);
        String refreshToken = jwtUtil.createJwt("refresh", loginResponse.userId(),
            loginResponse.role().name(), 86400000L);

        // Redis에 Refresh 토큰 저장
        refreshTokenService.saveRefreshToken(loginResponse.userId(), refreshToken, 86400L);

        response.setHeader("Authorization", "Bearer " + accessToken);

        return ResponseEntity.ok(loginResponse);
    }
}