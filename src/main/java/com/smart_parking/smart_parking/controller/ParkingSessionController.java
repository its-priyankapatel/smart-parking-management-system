package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.ParkingSessionExitRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionResponse;
import com.smart_parking.smart_parking.service.ParkingSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-session")
public class ParkingSessionController {

    private final ParkingSessionService parkingSessionService;

    public ParkingSessionController(ParkingSessionService parkingSessionService)
    {
        this.parkingSessionService=parkingSessionService;
    }

    @PostMapping("/create")
    public ResponseEntity<ParkingSessionResponse> createParkingSession(@RequestBody ParkingSessionRequest parkingSessionRequest)
    {
        ParkingSessionResponse response = parkingSessionService.addParkingSession(parkingSessionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/exit")
    public ResponseEntity<ParkingSessionResponse> setExitTime(@PathVariable Long id,@RequestBody ParkingSessionExitRequest parkingSessionExitRequest)
    {
        ParkingSessionResponse response=parkingSessionService.addExitTime(id,parkingSessionExitRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
