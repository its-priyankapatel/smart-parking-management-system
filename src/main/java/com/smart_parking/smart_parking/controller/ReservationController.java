package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.ReservationRequest;
import com.smart_parking.smart_parking.dto.ReservationResponse;
import com.smart_parking.smart_parking.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService)
    {
        this.reservationService=reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest)
    {
        ReservationResponse response = reservationService.doReservation(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
