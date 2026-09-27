package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.entity.ParkingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingLocationRepository extends JpaRepository<ParkingLocation,Long> {
     boolean existsByNameAndLocation(String name,String location);
}
