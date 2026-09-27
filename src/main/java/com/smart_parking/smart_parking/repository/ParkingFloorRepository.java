package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.entity.ParkingFloor;
import com.smart_parking.smart_parking.entity.ParkingLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingFloorRepository
        extends JpaRepository<ParkingFloor, Long> {

    boolean existsByFloorNumberAndParkingLocation(Integer floorNumber, ParkingLocation parkingLocation);
}