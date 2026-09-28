package com.smart_parking.smart_parking.dto;

import com.smart_parking.smart_parking.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Long userId;
}
