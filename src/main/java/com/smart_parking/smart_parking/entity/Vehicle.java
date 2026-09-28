package com.smart_parking.smart_parking.entity;

import com.smart_parking.smart_parking.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleNumber;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
