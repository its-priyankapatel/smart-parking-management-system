package com.smart_parking.smart_parking.validator;

import com.smart_parking.smart_parking.dto.VehicleRequest;

public class VehicleValidate {
    public static void vehicleValidate(VehicleRequest vehicleRequest) {
        if (vehicleRequest == null)
        {
            throw new IllegalArgumentException("Vehicle data is required");
        }
        if(vehicleRequest.getVehicleNumber()==null || vehicleRequest.getVehicleNumber().isBlank())
        {
            throw new IllegalArgumentException("Vehicle number is required");
        }
        if (!vehicleRequest.getVehicleNumber().trim().matches(
                "^[A-Z]{2}[0-9]{2}[A-Z]{1,3}[0-9]{1,4}$")) {

            throw new IllegalArgumentException(
                    "Invalid vehicle number format");
        }
        if(vehicleRequest.getVehicleType()==null)
        {
            throw new IllegalArgumentException("Vehicle type is required");
        }
        if(vehicleRequest.getUserId()==null)
        {
            throw new IllegalArgumentException("User Id is required");
        }
    }
}
