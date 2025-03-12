package com.lshzzz.mato.repository;

import com.lshzzz.mato.model.users.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    // 아이디로 삭제되지 않은 사용자 찾기
    Optional<Users> findByUserIdAndDeletedAtIsNull(String userId);

    // 아이디 중복 여부 확인
    boolean existsByUserId(String userId);
}