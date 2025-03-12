package com.lshzzz.mato.model.users.dto;

import com.lshzzz.mato.model.users.Role;

public record UsersRegisterResponse(

    Long id,
    String userId,
    String nickname,
    Role role
) {}