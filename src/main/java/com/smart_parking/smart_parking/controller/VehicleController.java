package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.VehicleRequest;
import com.smart_parking.smart_parking.dto.VehicleResponse;
import com.smart_parking.smart_parking.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService)
    {
        this.vehicleService=vehicleService;
    }
    @PostMapping("/register")
    public ResponseEntity<VehicleResponse>addVehicle(@RequestBody VehicleRequest vehicleRequest)
    {
        VehicleResponse response = vehicleService.registerVehicle(vehicleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
