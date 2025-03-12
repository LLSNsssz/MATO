package com.lshzzz.mato.model.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsersRegisterRequest(

    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4~20자로 입력해야 합니다.")
    String userId,

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6~20자로 입력해야 합니다.")
    String password,

    @NotBlank(message = "비밀번호 확인은 필수 입력 항목입니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6~20자로 입력해야 합니다.")
    String confirmPassword,

    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Size(max = 20, message = "닉네임은 최대 20자까지 가능합니다.")
    String nickname
) {}