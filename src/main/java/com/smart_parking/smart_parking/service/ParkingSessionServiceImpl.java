
package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingSessionRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionResponse;
import com.smart_parking.smart_parking.entity.ParkingSession;
import com.smart_parking.smart_parking.entity.Reservation;
import com.smart_parking.smart_parking.repository.ParkingSessionRepository;
import com.smart_parking.smart_parking.repository.ReservationRepository;
import com.smart_parking.smart_parking.validator.ParkingSessionValidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.smart_parking.smart_parking.enums.ParkingSessionStatus.ACTIVE;
import static com.smart_parking.smart_parking.enums.ParkingSessionStatus.COMPLETED;
import static com.smart_parking.smart_parking.enums.ReservationStatus.RESERVED;

@Service
public class ParkingSessionServiceImpl implements ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final ReservationRepository reservationRepository;

    public ParkingSessionServiceImpl(
            ParkingSessionRepository parkingSessionRepository,
            ReservationRepository reservationRepository) {

        this.parkingSessionRepository = parkingSessionRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public ParkingSessionResponse addParkingSession(
            ParkingSessionRequest parkingSessionRequest) {

        ParkingSessionValidate.parkingSessionValidate(
                parkingSessionRequest
        );

        Reservation reservation =
                reservationRepository.findById(
                        parkingSessionRequest.getReservationId()
                ).orElseThrow(() ->
                        new IllegalArgumentException("Reservation not found"));

        if (!reservation.getStatus().equals(RESERVED)) {
            throw new IllegalArgumentException(
                    "Reservation must be reserved"
            );
        }

        boolean isParkingSessionExists =
                parkingSessionRepository.existsByReservationId(
                        parkingSessionRequest.getReservationId()
                );

        if (isParkingSessionExists) {
            throw new IllegalArgumentException(
                    "Parking Session already exists"
            );
        }

        LocalDateTime entryTime = LocalDateTime.now();

        if (entryTime.isBefore(reservation.getStartTime())) {
            throw new IllegalArgumentException(
                    "Vehicle cannot enter before reservation start time"
            );
        }

        ParkingSession parkingSession = new ParkingSession();

        parkingSession.setReservation(reservation);
        parkingSession.setEntryTime(entryTime);
        parkingSession.setParkingSessionStatus(ACTIVE);

        ParkingSession newParkingSession =
                parkingSessionRepository.save(parkingSession);

        return new ParkingSessionResponse(
                true,
                "Parking Session Created Successfully",
                newParkingSession
        );
    }

    @Override
    @Transactional
    public ParkingSessionResponse addExitTime(Long parkingSessionId) {

        ParkingSession parkingSession =
                parkingSessionRepository.findById(parkingSessionId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Parking session not found"
                                ));

        if (parkingSession.getParkingSessionStatus() != ACTIVE) {
            throw new IllegalArgumentException(
                    "Parking Session status should be active"
            );
        }

        if (parkingSession.getEntryTime() == null) {
            throw new IllegalArgumentException(
                    "Parking session entry time not found"
            );
        }

        LocalDateTime exitTime = LocalDateTime.now();

        if (exitTime.isBefore(parkingSession.getEntryTime())) {
            throw new IllegalArgumentException(
                    "Exit time should be greater than entry time"
            );
        }

        parkingSession.setExitTime(exitTime);
        parkingSession.setParkingSessionStatus(COMPLETED);

        ParkingSession updatedParkingSession =
                parkingSessionRepository.save(parkingSession);

        return new ParkingSessionResponse(
                true,
                "Parking Session Completed Successfully",
                updatedParkingSession
        );
    }
}
