package com.lshzzz.mato.model.users.dto;

import com.lshzzz.mato.model.users.Role;

public record UsersUpdateResponse(

    Long id,
    String userId,
    String nickname,
    Role role
) {}