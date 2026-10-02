package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.entity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession,Long> {
    boolean existsByReservationId(Long reservationId);
}
