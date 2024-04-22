package ru.ardyc.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ardyc.travelagency.dto.request.AuthenticationRequest;
import ru.ardyc.travelagency.dto.request.RegistrationRequest;
import ru.ardyc.travelagency.dto.response.UserResponse;
import ru.ardyc.travelagency.service.AuthenticationService;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody RegistrationRequest registerUserDto) {
        var user = authenticationService.signUp(registerUserDto);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + user.jwtToken())
                .body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        var user = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + user.jwtToken())
                .body(user);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> getUser(@RequestHeader("Authorization") String token) {
        var user = authenticationService.getUser(token.split(" ")[1]);
        return ResponseEntity.ok().body(user);
    }
}
