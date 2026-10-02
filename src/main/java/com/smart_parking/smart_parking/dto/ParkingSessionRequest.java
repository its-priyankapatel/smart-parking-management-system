package com.smart_parking.smart_parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSessionRequest {
    private Long reservationId;
    private LocalDateTime entryTime;
}
