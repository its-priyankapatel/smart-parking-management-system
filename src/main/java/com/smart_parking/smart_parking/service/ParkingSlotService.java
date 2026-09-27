package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingSlotRequest;
import com.smart_parking.smart_parking.dto.ParkingSlotResponse;

public interface ParkingSlotService {
    ParkingSlotResponse addParkingSlot(ParkingSlotRequest parkingSlotRequest);
}
