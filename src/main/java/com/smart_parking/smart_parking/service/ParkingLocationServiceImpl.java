package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.validator.Validate;
import com.smart_parking.smart_parking.dto.ParkingLocationRequest;
import com.smart_parking.smart_parking.dto.ParkingLocationResponse;
import com.smart_parking.smart_parking.entity.ParkingLocation;
import com.smart_parking.smart_parking.repository.ParkingLocationRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingLocationServiceImpl implements ParkingLocationService{
    private final ParkingLocationRepository parkingLocationRepository;
    public ParkingLocationServiceImpl(ParkingLocationRepository parkingLocationRepository)
    {
        this.parkingLocationRepository=parkingLocationRepository;
    }
    @Override
    public ParkingLocationResponse createParkingLocation(ParkingLocationRequest parkingLocationRequest)
    {
        Validate.parkingLocationValidate(parkingLocationRequest);

    boolean isExist = parkingLocationRepository.existsByNameAndLocation(parkingLocationRequest.getName().trim(), parkingLocationRequest.getLocation().trim());
    if(isExist)
    {
        throw new IllegalArgumentException("Parking location already exists");
    }
        ParkingLocation parkingLocation=changer(parkingLocationRequest);


        ParkingLocation savedLocation= parkingLocationRepository.save(parkingLocation);
        return new ParkingLocationResponse(true,"Parking Location Created Successfully",savedLocation);
    }
    public ParkingLocation changer(ParkingLocationRequest parkingLocationRequest)
    {

        ParkingLocation parkingLocation=new ParkingLocation();
        parkingLocation.setName(parkingLocationRequest.getName());
        parkingLocation.setLocation(parkingLocationRequest.getLocation());
        return parkingLocation;
    }
}
