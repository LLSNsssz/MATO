package com.lshzzz.mato.utils.users;

import com.lshzzz.mato.model.users.Role;
import com.lshzzz.mato.model.users.Users;
import com.lshzzz.mato.model.users.dto.UsersLoginResponse;
import com.lshzzz.mato.model.users.dto.UsersRegisterRequest;
import com.lshzzz.mato.model.users.dto.UsersRegisterResponse;
import com.lshzzz.mato.model.users.dto.UsersUpdateResponse;

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

    // Entity → UsersRegisterResponse DTO
    public static UsersRegisterResponse toRegisterResponse(Users user) {
        return new UsersRegisterResponse(
            user.getId(),
            user.getUserId(),
            user.getNickname(),
            user.getRole()
        );
    }

    // UsersRegisterRequest DTO → Entity
    public static Users toEntity(UsersRegisterRequest request, String encodedPassword) {
        return Users.builder()
            .userId(request.userId())
            .password(encodedPassword)
            .nickname(request.nickname())
            .role(Role.USER)
            .build();
    }

    // Entity → UserUpdateResponse DTO
    public static UsersUpdateResponse toUpdateResponse(Users user) {
        return new UsersUpdateResponse(
            user.getId(),
            user.getUserId(),
            user.getNickname(),
            user.getRole()
        );
    }
}