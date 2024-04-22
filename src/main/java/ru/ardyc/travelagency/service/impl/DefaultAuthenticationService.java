package ru.ardyc.travelagency.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ardyc.travelagency.domain.model.UserEntity;
import ru.ardyc.travelagency.domain.repository.UserRepository;
import ru.ardyc.travelagency.dto.request.AuthenticationRequest;
import ru.ardyc.travelagency.dto.request.RegistrationRequest;
import ru.ardyc.travelagency.dto.response.UserResponse;
import ru.ardyc.travelagency.service.AuthenticationService;
import ru.ardyc.travelagency.service.security.JwtService;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email()).orElseThrow();
        return user.toResponse()
                .jwtToken(jwtService.generateToken(user.getEmail(), user.getId()))
                .build();
    }

    @Override
    public UserResponse signUp(RegistrationRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            return authenticate(new AuthenticationRequest(request.email(), request.password()));
        }
        UserEntity user = UserEntity.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        return userRepository.save(user).toResponse()
                .jwtToken(jwtService.generateToken(user.getEmail(), user.getId()))
                .build();
    }

    @Override
    public UserResponse getUser(String token) {
        return userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow().toResponse().build();
    }
}