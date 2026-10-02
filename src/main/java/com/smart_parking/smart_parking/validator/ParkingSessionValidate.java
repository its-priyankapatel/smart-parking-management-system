package com.smart_parking.smart_parking.validator;

import com.smart_parking.smart_parking.dto.ParkingSessionRequest;

public class ParkingSessionValidate {
    public static void parkingSessionValidate(ParkingSessionRequest parkingSessionRequest)
    {
        if(parkingSessionRequest==null)
        {
            throw new IllegalArgumentException("Parking Session data is required");
        }
        if(parkingSessionRequest.getReservationId()==null)
        {
            throw new IllegalArgumentException("Reservation Id is required");
        }
        if(parkingSessionRequest.getEntryTime()==null)
        {
            throw new IllegalArgumentException("Entry Time is required");
        }
    }
}
