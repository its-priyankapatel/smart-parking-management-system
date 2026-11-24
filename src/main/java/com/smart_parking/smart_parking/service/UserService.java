package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.LoginRequest;
import com.smart_parking.smart_parking.dto.LoginResponse;
import com.smart_parking.smart_parking.dto.UserRequest;
import com.smart_parking.smart_parking.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    LoginResponse loginUser(LoginRequest loginRequest);
}
