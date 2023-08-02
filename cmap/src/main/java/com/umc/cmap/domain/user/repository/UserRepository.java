package com.umc.cmap.domain.user.repository;

import com.umc.cmap.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByIdx(Long idx);

    Optional<User> findByNickname(String nickname);
}
