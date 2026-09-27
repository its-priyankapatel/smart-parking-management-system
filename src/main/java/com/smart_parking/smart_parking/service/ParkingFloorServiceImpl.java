package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingFloorRequest;
import com.smart_parking.smart_parking.dto.ParkingFloorResponse;
import com.smart_parking.smart_parking.entity.ParkingFloor;
import com.smart_parking.smart_parking.entity.ParkingLocation;
import com.smart_parking.smart_parking.repository.ParkingFloorRepository;
import com.smart_parking.smart_parking.repository.ParkingLocationRepository;
import org.springframework.stereotype.Service;
import com.smart_parking.smart_parking.validator.Validate;

@Service
public class ParkingFloorServiceImpl implements ParkingFloorService {
    private final ParkingFloorRepository parkingFloorRepository;
    private  final ParkingLocationRepository parkingLocationRepository;
    public ParkingFloorServiceImpl(ParkingFloorRepository parkingFloorRepository,ParkingLocationRepository parkingLocationRepository)
    {
        this.parkingFloorRepository = parkingFloorRepository;
        this.parkingLocationRepository=parkingLocationRepository;
    }
    @Override
    public ParkingFloorResponse addParkingFloor(ParkingFloorRequest parkingFloorRequest)
    {
         Validate.parkingFloorValidate(parkingFloorRequest);
         ParkingLocation parkingLocation = parkingLocationRepository.findById(parkingFloorRequest.getParkingLocationId()).orElseThrow(()->new IllegalArgumentException("Parking Location not found"));
         boolean isFloorNumberExist = parkingFloorRepository.existsByFloorNumberAndParkingLocation(parkingFloorRequest.getFloorNumber(),parkingLocation);
         if(isFloorNumberExist)
         {
             throw new IllegalArgumentException("Floor Number Already Exist for this parking location");
         }
         ParkingFloor parkingFloor=new ParkingFloor();
         parkingFloor.setFloorNumber(parkingFloorRequest.getFloorNumber());
         parkingFloor.setParkingLocation(parkingLocation);
        ParkingFloor newParkingFloor = parkingFloorRepository.save(parkingFloor);
        return new ParkingFloorResponse(true,"Parking floor created successfully",newParkingFloor);
    }
}
