package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingLocationRequest;
import com.smart_parking.smart_parking.dto.ParkingLocationResponse;
import com.smart_parking.smart_parking.entity.ParkingLocation;

import java.util.Optional;

public interface ParkingLocationService {
    ParkingLocationResponse createParkingLocation(ParkingLocationRequest parkingLocationRequest);
}
