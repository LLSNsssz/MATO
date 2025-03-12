package com.lshzzz.mato.model.users.dto;

import com.lshzzz.mato.model.users.Role;

public record UsersLoginResponse(

    Long id,
    String userId,
    String nickname,
    Role role
) {}