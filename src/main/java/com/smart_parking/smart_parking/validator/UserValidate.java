package com.smart_parking.smart_parking.validator;

import com.smart_parking.smart_parking.dto.UserRequest;

public class UserValidate {

    public static void userValidate(UserRequest userRequest) {

        if (userRequest == null) {
            throw new IllegalArgumentException("User data is required");
        }

        if (userRequest.getName() == null ||
                userRequest.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (userRequest.getName().trim().length() < 3) {
            throw new IllegalArgumentException(
                    "User name should be at least 3 characters");
        }

        if (userRequest.getEmail() == null ||
                userRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (!userRequest.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException(
                    "Invalid email format");
        }

        if (userRequest.getPassword() == null ||
                userRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (userRequest.getPassword().length() < 8) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters");
        }

        if (userRequest.getMobileNumber() == null ||
                userRequest.getMobileNumber().isBlank()) {
            throw new IllegalArgumentException(
                    "Mobile number is required");
        }

        if (!userRequest.getMobileNumber().matches("\\d{10}")) {
            throw new IllegalArgumentException(
                    "Mobile number must contain 10 digits");
        }
    }
}