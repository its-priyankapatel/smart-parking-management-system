package com.smart_parking.smart_parking.repository;

import com.smart_parking.smart_parking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    boolean existsByParkingSessionId(Long parkingSessionId);
}
