package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.ParkingSlotRequest;
import com.smart_parking.smart_parking.dto.ParkingSlotResponse;
import com.smart_parking.smart_parking.service.ParkingSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-slot")
public class ParkingSlotController {
    private final ParkingSlotService parkingSlotService;
    public ParkingSlotController(ParkingSlotService parkingSlotService)
    {
        this.parkingSlotService=parkingSlotService;
    }
    @PostMapping("/create")
    ResponseEntity<ParkingSlotResponse> createParkingSlot(@RequestBody ParkingSlotRequest parkingSlotRequest)
    {
       ParkingSlotResponse response = parkingSlotService.addParkingSlot(parkingSlotRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
