package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.ParkingSessionRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionResponse;
import com.smart_parking.smart_parking.service.ParkingSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-session")
public class ParkingSessionController {

    private final ParkingSessionService parkingSessionService;

    public ParkingSessionController(ParkingSessionService parkingSessionService)
    {
        this.parkingSessionService=parkingSessionService;
    }

    @PostMapping("/create")
    ResponseEntity<ParkingSessionResponse> createParkingSession(@RequestBody ParkingSessionRequest parkingSessionRequest)
    {
        ParkingSessionResponse response = parkingSessionService.addParkingSession(parkingSessionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
