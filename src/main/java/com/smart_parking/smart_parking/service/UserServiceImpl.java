package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.LoginRequest;
import com.smart_parking.smart_parking.dto.LoginResponse;
import com.smart_parking.smart_parking.dto.UserRequest;
import com.smart_parking.smart_parking.dto.UserResponse;
import com.smart_parking.smart_parking.entity.User;
import com.smart_parking.smart_parking.enums.UserRole;
import com.smart_parking.smart_parking.repository.UserRepository;
import com.smart_parking.smart_parking.validator.UserValidate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtService jwtService)
    {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }
    @Override
    public UserResponse createUser(UserRequest userRequest)
    {
        UserValidate.userValidate(userRequest);
       boolean isUserExist = userRepository.existsByEmail(userRequest.getEmail());
       if(isUserExist)
       {
         throw new IllegalArgumentException("User already exists");
       }

       User user=new User();
       user.setName(userRequest.getName());
       user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
       user.setEmail(userRequest.getEmail());
       user.setMobileNumber(userRequest.getMobileNumber());
       user.setRole(UserRole.USER);

       User newUser = userRepository.save(user);
       return new UserResponse(true,"User Registered Successfully", newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getMobileNumber(),newUser.getRole());
    }

    @Override

    public LoginResponse loginUser(LoginRequest loginRequest) {

        UserValidate.loginUserValidate(loginRequest);

        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        }catch(AuthenticationException exception)
        {
           throw new BadCredentialsException("Invalid email or password");
        }

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid email or password"));

        // Generate JWT
        String token = jwtService.generateToken(user);

        return new LoginResponse(
                true,
                "User Logged In Successfully",
                token,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

}
