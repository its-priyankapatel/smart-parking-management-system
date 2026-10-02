package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.entity.ParkingSlot;
import com.smart_parking.smart_parking.entity.Reservation;
import com.smart_parking.smart_parking.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
       boolean existsByParkingSlotIdAndStartTimeLessThanAndEndTimeGreaterThanAndStatus(Long parkingSlotId, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status);

}
