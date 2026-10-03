package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingSessionExitRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionResponse;

public interface ParkingSessionService {
    ParkingSessionResponse addParkingSession(ParkingSessionRequest parkingSessionRequest);
    ParkingSessionResponse addExitTime(Long id, ParkingSessionExitRequest parkingSessionExistRequest);
}
