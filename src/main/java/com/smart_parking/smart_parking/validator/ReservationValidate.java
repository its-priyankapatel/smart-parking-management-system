package com.smart_parking.smart_parking.validator;

import com.smart_parking.smart_parking.dto.ReservationRequest;

public class ReservationValidate {
    public static void reservationValidate(ReservationRequest reservationRequest)
    {
        if(reservationRequest==null)
        {
            throw new IllegalArgumentException("Reservation data is required");
        }
        if(reservationRequest.getUserId()==null)
        {
            throw new IllegalArgumentException("User Id is required");
        }
        if(reservationRequest.getVehicleId()==null)
        {
            throw new IllegalArgumentException("Vehicle Id is required");
        }
        if(reservationRequest.getParkingSlotId()==null)
        {
            throw new IllegalArgumentException("Parking slot id is required");
        }
        if(reservationRequest.getStartTime()==null)
        {
            throw new IllegalArgumentException("Starting time is required");
        }
        if(reservationRequest.getEndTime()==null)
        {
            throw new IllegalArgumentException("Ending time is required");
        }
        if(!reservationRequest.getStartTime().isBefore(reservationRequest.getEndTime()))
        {
            throw new IllegalArgumentException("Starting time must be before ending time");
        }
    }
}
