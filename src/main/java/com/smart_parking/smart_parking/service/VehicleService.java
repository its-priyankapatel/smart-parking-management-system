package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.VehicleRequest;
import com.smart_parking.smart_parking.dto.VehicleResponse;

public interface VehicleService {
  VehicleResponse registerVehicle(VehicleRequest vehicleRequest);
}
