package com.smart_parking.smart_parking.dto;

import com.smart_parking.smart_parking.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse {
    private boolean status;
    private String message;
    private Vehicle vehicle;
}
