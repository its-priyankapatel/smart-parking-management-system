package com.smart_parking.smart_parking.dto;

import com.smart_parking.smart_parking.entity.ParkingLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLocationResponse {
    private boolean status;
    private String message;
    private ParkingLocation parkingLocation;
}
