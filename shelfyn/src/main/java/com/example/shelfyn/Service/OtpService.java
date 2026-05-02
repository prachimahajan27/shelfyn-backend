package com.example.shelfyn.Service;

import com.example.shelfyn.Entity.Otp;
import com.example.shelfyn.Entity.RefreshToken;
import com.example.shelfyn.Entity.User;
import com.example.shelfyn.JwtAuth.JwtUtil;
import com.example.shelfyn.model.AuthResponse;
import com.example.shelfyn.model.VerifyOtpRequest;
import com.example.shelfyn.repository.OtpRepository;
import com.example.shelfyn.repository.RefreshTokenRepository;
import com.example.shelfyn.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OtpService {


    private final OtpRepository otpRepository;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    public String generateOtp(String email) {

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiry(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otpEntity);

        return otp;
    }
    public AuthResponse verifyOtp(VerifyOtpRequest request) {

        User user = userRepository.findByEmail(request.email)
                .orElseGet(() -> {
                    User u = new User();
                    u.setEmail(request.email);
                    return userRepository.save(u);
                });

        // validate OTP (you already have this)

        String accessToken = JwtUtil.generateToken(user.getEmail());

        String refreshToken = UUID.randomUUID().toString();

        RefreshToken token = new RefreshToken();
        token.setToken(refreshToken);
        token.setEmail(user.getEmail());
        token.setExpiry(LocalDateTime.now().plusDays(7));

        refreshTokenRepository.save(token);

        return new AuthResponse(accessToken, refreshToken, user.getEmail());
    }
    public AuthResponse refreshToken(String refreshToken) {

        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.getExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        String newAccessToken = JwtUtil.generateToken(token.getEmail());

        return new AuthResponse(newAccessToken, refreshToken, token.getEmail());
    }
    @Transactional
    public void logout(String refreshToken) {
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}
