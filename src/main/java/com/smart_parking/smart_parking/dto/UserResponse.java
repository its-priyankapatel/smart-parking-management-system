package com.smart_parking.smart_parking.dto;

import com.smart_parking.smart_parking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private boolean status;
    private String message;
    private User user;
}
