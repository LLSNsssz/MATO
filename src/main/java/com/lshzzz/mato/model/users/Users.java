package com.lshzzz.mato.model.users;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    // ID(PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User ID
    @Column(nullable = false, unique = true, length = 20)
    private String userId;

    // Password
    @Column(nullable = false, length = 20)
    private String password;

    // Nickname
    @Column(nullable = false, length = 20)
    private String nickname;

    // Role
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    // Created At
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Updated At
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Deleted At
    @Column
    private LocalDateTime deletedAt;

    @Builder
    public Users(String userId, String password, String nickname, Role role) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    // 닉네임 수정 메서드
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    // 비밀번호 수정 메서드
    public void updatePassword(String password) {
        this.password = password;
    }

    // 소프트 딜리트 메서드
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}