package ru.ardyc.travelagency.service;

import ru.ardyc.travelagency.dto.request.AuthenticationRequest;
import ru.ardyc.travelagency.dto.request.RegistrationRequest;
import ru.ardyc.travelagency.dto.response.UserResponse;

public interface AuthenticationService {

    UserResponse authenticate(AuthenticationRequest request);

    UserResponse signUp(RegistrationRequest request);

}
