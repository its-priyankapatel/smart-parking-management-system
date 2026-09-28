package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.entity.User;
import com.smart_parking.smart_parking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    boolean existsByVehicleNumber(String vehicleNumber);
}
