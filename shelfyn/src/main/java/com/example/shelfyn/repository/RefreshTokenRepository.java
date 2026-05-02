package com.example.shelfyn.repository;

import com.example.shelfyn.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByEmail(String email);

    void deleteByToken(String refreshToken);
}
