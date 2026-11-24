package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.LoginRequest;
import com.smart_parking.smart_parking.dto.LoginResponse;
import com.smart_parking.smart_parking.dto.UserRequest;
import com.smart_parking.smart_parking.dto.UserResponse;
import com.smart_parking.smart_parking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService=userService;
    }

     @PostMapping("/register")
     ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest)
     {
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     @PostMapping("/login")
     ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest)
     {
      LoginResponse response = userService.loginUser(loginRequest);
      return ResponseEntity.status(HttpStatus.OK).body(response);
     }
}
