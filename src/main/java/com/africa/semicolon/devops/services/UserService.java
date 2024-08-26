package com.africa.semicolon.devops.services;

import com.africa.semicolon.devops.dto.requests.RegisterRequest;
import com.africa.semicolon.devops.dto.responses.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);

}
