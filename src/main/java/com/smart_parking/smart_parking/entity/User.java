package com.smart_parking.smart_parking.entity;

import com.smart_parking.smart_parking.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    @Enumerated(value = EnumType.STRING)
    private UserRole role = UserRole.USER;
}


