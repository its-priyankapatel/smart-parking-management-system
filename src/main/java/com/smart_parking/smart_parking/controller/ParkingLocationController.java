package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.ParkingLocationRequest;
import com.smart_parking.smart_parking.dto.ParkingLocationResponse;
import com.smart_parking.smart_parking.entity.ParkingLocation;
import com.smart_parking.smart_parking.service.ParkingLocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-location")
public class ParkingLocationController {
    private final ParkingLocationService parkingLocationService;
    public ParkingLocationController(ParkingLocationService parkingLocationService)
    {
        this.parkingLocationService=parkingLocationService;
    }
    @PostMapping("/create")
    public ResponseEntity<ParkingLocationResponse> addParkingLocation(@RequestBody ParkingLocationRequest parkingLocationRequest)
    {
        ParkingLocationResponse response = parkingLocationService.createParkingLocation(parkingLocationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
