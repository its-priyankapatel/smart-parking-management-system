package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.ParkingFloorRequest;
import com.smart_parking.smart_parking.dto.ParkingFloorResponse;
import com.smart_parking.smart_parking.service.ParkingFloorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-floor")
public class ParkingFloorController {
   private final ParkingFloorService parkingFloorService;
   public ParkingFloorController(ParkingFloorService parkingFloorService)
   {
       this.parkingFloorService=parkingFloorService;
   }
   @PostMapping("/create")
    ResponseEntity<ParkingFloorResponse> createParkingFloor(@RequestBody ParkingFloorRequest parkingFloorRequest)
   {
      ParkingFloorResponse response = parkingFloorService.addParkingFloor(parkingFloorRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }
}
