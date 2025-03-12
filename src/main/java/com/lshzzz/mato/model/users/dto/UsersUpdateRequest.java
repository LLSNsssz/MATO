package com.lshzzz.mato.model.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsersUpdateRequest(

    @Size(max = 20, message = "닉네임은 최대 20자까지 가능합니다.")
    String nickname,

    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    String currentPassword,

    @Size(min = 6, max = 20, message = "비밀번호는 6~20자로 입력해야 합니다.")
    String newPassword
) {}