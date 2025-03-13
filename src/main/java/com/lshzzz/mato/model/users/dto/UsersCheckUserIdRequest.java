package com.lshzzz.mato.model.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsersCheckUserIdRequest(

    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4~20자로 입력해야 합니다.")
    String userId
) {}