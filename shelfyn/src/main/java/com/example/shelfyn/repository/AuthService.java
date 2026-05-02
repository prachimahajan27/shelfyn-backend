//package com.example.shelfyn.repository;
//
//import com.example.shelfyn.Entity.User;
//import com.example.shelfyn.JwtAuth.JwtUtil;
//import com.example.shelfyn.model.AuthResponse;
//import com.example.shelfyn.model.LoginRequest;
//import com.example.shelfyn.model.SignupRequest;
//import com.example.shelfyn.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    private final UserRepository userRepository;
//
//    public AuthService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public String signup(SignupRequest request) {
//        User user = new User();
//        user.setName(request.name);
//        user.setEmail(request.email);
//        user.setPassword(request.password); // later hash it
//
//        try {
//            userRepository.save(user);
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }
//
//        return "User registered";
//    }
//
//    public AuthResponse login(LoginRequest request) {
//        User user = userRepository.findByEmail(request.email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!user.getPassword().equals(request.password)) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        String token = JwtUtil.generateToken(user.getEmail());
//
//        return new AuthResponse(token, user.getEmail());
//    }
//}