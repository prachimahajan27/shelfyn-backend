package com.example.shelfyn.repository;

import com.example.shelfyn.Entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByEmailOrderByExpiryDesc(String email);

}