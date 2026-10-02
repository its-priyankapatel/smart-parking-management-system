package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ReservationRequest;
import com.smart_parking.smart_parking.dto.ReservationResponse;

public interface ReservationService {
    ReservationResponse doReservation(ReservationRequest reservationRequest);
}
