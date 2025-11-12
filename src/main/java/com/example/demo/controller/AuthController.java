package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtils;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.Set;

@Tag(name = "Authentication", description = "User registration and login")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.authService = authService;
    }

    @Operation(summary = "Register a User")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        // default role USER
        User created = authService.register(req.getUsername(), req.getPassword(), Set.of("ROLE_USER"));
        return ResponseEntity.ok("User created: " + created.getUsername());
    }

    @Operation(summary = "Login a User")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        // extract roles
        Set<String> roles = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(java.util.stream.Collectors.toSet());
        String token = jwtUtils.generateJwtToken(userDetails.getUsername(), roles);
        AuthResponse resp = new AuthResponse(token, "Bearer");
        return ResponseEntity.ok(resp);
    }
}
