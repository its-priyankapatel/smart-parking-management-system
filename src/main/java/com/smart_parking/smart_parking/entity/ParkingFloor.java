package com.smart_parking.smart_parking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Parking_floors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer floorNumber;
    @ManyToOne
    @JoinColumn(name = "parking_location_id")
    private ParkingLocation parkingLocation;
}
