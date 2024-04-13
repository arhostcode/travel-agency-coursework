package ru.ardyc.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ardyc.travelagency.dto.request.AuthenticationRequest;
import ru.ardyc.travelagency.dto.request.RegistrationRequest;
import ru.ardyc.travelagency.dto.response.UserResponse;
import ru.ardyc.travelagency.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
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
}
