package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingFloorRequest;
import com.smart_parking.smart_parking.dto.ParkingFloorResponse;
import com.smart_parking.smart_parking.dto.ParkingLocationRequest;

public interface ParkingFloorService {
    ParkingFloorResponse addParkingFloor(ParkingFloorRequest parkingFloorRequest);
}
