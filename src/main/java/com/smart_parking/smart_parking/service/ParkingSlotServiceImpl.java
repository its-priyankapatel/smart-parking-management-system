package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingSlotRequest;
import com.smart_parking.smart_parking.dto.ParkingSlotResponse;
import com.smart_parking.smart_parking.entity.ParkingFloor;
import com.smart_parking.smart_parking.entity.ParkingSlot;
import com.smart_parking.smart_parking.repository.ParkingFloorRepository;
import com.smart_parking.smart_parking.repository.ParkingSlotRepository;
import com.smart_parking.smart_parking.validator.Validate;
import org.springframework.stereotype.Service;


@Service
public class ParkingSlotServiceImpl implements ParkingSlotService{
    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingFloorRepository parkingFloorRepository;
    public ParkingSlotServiceImpl(ParkingSlotRepository parkingSlotRepository,ParkingFloorRepository parkingFloorRepository)
    {
        this.parkingSlotRepository=parkingSlotRepository;
        this.parkingFloorRepository=parkingFloorRepository;
    }
    @Override
    public ParkingSlotResponse addParkingSlot(ParkingSlotRequest parkingSlotRequest)
    {
        Validate.parkingSlotValidate(parkingSlotRequest);
        ParkingFloor parkingFloor = parkingFloorRepository.findById(parkingSlotRequest.getParkingFloorId()).orElseThrow(()->new IllegalArgumentException("Parking floor not found"));
        boolean isParkingSlotExist = parkingSlotRepository.existsBySlotNumberAndParkingFloor(parkingSlotRequest.getSlotNumber().trim(),parkingFloor);
        if(isParkingSlotExist)
        {
            throw new IllegalArgumentException("Parking Slot already exists");
        }
        ParkingSlot parkingSlot=new ParkingSlot();
        parkingSlot.setSlotNumber(parkingSlotRequest.getSlotNumber().trim());
        parkingSlot.setParkingFloor(parkingFloor);
       ParkingSlot newParkingSlot = parkingSlotRepository.save(parkingSlot);
       return new ParkingSlotResponse(true ,"Parking Slot Created Successfully",newParkingSlot);
    }
}
