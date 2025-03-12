package com.lshzzz.mato.utils.users;

import com.lshzzz.mato.model.users.Users;
import com.lshzzz.mato.model.users.dto.UsersLoginResponse;

public class UsersMapper {

    // Entity → UsersLoginResponse DTO
    public static UsersLoginResponse toLoginResponse(Users user) {
        return new UsersLoginResponse(
            user.getId(),
            user.getUserId(),
            user.getNickname(),
            user.getRole()
        );
    }
}