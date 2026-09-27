package com.smart_parking.smart_parking.validator;

import com.smart_parking.smart_parking.dto.ParkingFloorRequest;
import com.smart_parking.smart_parking.dto.ParkingLocationRequest;
import com.smart_parking.smart_parking.dto.ParkingSlotRequest;

public class Validate
{
    public static void parkingLocationValidate(ParkingLocationRequest parkingLocationRequest)
    {
        if(parkingLocationRequest==null)
        {
            throw new IllegalArgumentException("Parking location data is required");
        }
        if ((parkingLocationRequest.getName() == null ||
                parkingLocationRequest.getName().isBlank())
                &&
                (parkingLocationRequest.getLocation() == null ||
                        parkingLocationRequest.getLocation().isBlank())) {

            throw new IllegalArgumentException(
                    "Parking location data is required");
        }
        if(parkingLocationRequest.getName()==null || parkingLocationRequest.getName().isBlank())
        {
            throw new IllegalArgumentException("Parking location name is required");
        }
        if(parkingLocationRequest.getName().trim().length()<3)
        {
            throw new IllegalArgumentException("Parking location should be at least 3 characters");
        }
        if(parkingLocationRequest.getLocation()==null || parkingLocationRequest.getLocation().isBlank())
        {
            throw new IllegalArgumentException("Parking location is required");
        }
        if(parkingLocationRequest.getLocation().trim().length()<3)
        {
            throw new IllegalArgumentException("Parking location should be at least 3 characters");
        }
    }
    public static void parkingFloorValidate(ParkingFloorRequest parkingFloorRequest)
    {
        if(parkingFloorRequest==null)
        {
            throw new IllegalArgumentException("parking floor data is required");
        }
        if(parkingFloorRequest.getFloorNumber()==null)
        {
            throw new IllegalArgumentException("Floor Number is required");
        }
        if(parkingFloorRequest.getFloorNumber()<0)
        {
            throw new IllegalArgumentException("Floor number should be valid");
        }
        if (parkingFloorRequest.getParkingLocationId() == null) {
            throw new IllegalArgumentException("Parking location ID is required");
        }
    }
    public static void parkingSlotValidate(ParkingSlotRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("Parking slot data is required");
        }

        if (request.getSlotNumber() == null ||
                request.getSlotNumber().isBlank()) {
            throw new IllegalArgumentException("Parking slot number is required");
        }

        if (request.getParkingFloorId() == null) {
            throw new IllegalArgumentException("Parking floor ID is required");
        }
    }
}
