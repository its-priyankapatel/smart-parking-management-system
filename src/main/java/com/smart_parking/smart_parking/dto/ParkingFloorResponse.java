package com.smart_parking.smart_parking.dto;

import com.smart_parking.smart_parking.entity.ParkingFloor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingFloorResponse {
    private boolean status;
    private String message;
    private ParkingFloor parkingFloor;
}
