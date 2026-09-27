package com.smart_parking.smart_parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingFloorRequest {
    private Integer floorNumber;
    private Long parkingLocationId;
}
