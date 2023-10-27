package com.hackathon.dronedelivery.auth;


import com.hackathon.dronedelivery.model.User;
import com.hackathon.dronedelivery.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, String.valueOf(response.getToken())).body(response.getUser());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, String.valueOf(response.getToken())).body(response.getUser());
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validationToken (@RequestParam("token") String token, @AuthenticationPrincipal User user) {
        boolean isValidateToken = jwtUtil.isTokenValid(token, user);
        return ResponseEntity.ok(isValidateToken);
    }
}
