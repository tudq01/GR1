package com.gr1.spring.repository;

import java.util.Optional;

import com.gr1.spring.entity.RefreshToken;
import com.gr1.spring.entity.Todo;
import com.gr1.spring.entity.User;
import com.gr1.spring.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
public interface RefreshTokenRepository extends BaseRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
