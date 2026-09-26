package Validator;

import com.smart_parking.smart_parking.dto.ParkingLocationRequest;

public class Validate {
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
}
