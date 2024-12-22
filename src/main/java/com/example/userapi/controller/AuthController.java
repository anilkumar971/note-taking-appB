package com.example.userapi.controller;


import com.example.userapi.dto.LoginRequest;
import com.example.userapi.dto.SignupRequest;
import com.example.userapi.entity.User;
import com.example.userapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {
        User user = authService.signup(signupRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = authService.login(loginRequest);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).body(null));  // Unauthorized if login fails
    }
}
