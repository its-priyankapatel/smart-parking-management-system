package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.UserRequest;
import com.smart_parking.smart_parking.dto.UserResponse;
import com.smart_parking.smart_parking.entity.User;
import com.smart_parking.smart_parking.repository.UserRepository;
import com.smart_parking.smart_parking.validator.UserValidate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
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
       user.setPassword(userRequest.getPassword());
       user.setEmail(userRequest.getEmail());
       user.setMobileNumber(userRequest.getMobileNumber());

       User newUser = userRepository.save(user);
       return new UserResponse(true,"User Registered Successfully",newUser);
    }
}
