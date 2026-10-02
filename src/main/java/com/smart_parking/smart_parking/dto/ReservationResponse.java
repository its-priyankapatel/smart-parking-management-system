package com.smart_parking.smart_parking.dto;

import com.smart_parking.smart_parking.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private boolean status;
    private String message;
    private Reservation reservation;
}
