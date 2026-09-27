package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.entity.ParkingFloor;
import com.smart_parking.smart_parking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long> {
    boolean existsBySlotNumberAndParkingFloor(String slotNumber, ParkingFloor parkingFloor);
}
