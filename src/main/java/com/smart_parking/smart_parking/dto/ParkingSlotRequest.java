package com.smart_parking.smart_parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSlotRequest {
    private String slotNumber;
    private Long parkingFloorId;
}
