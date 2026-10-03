package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingSessionExitRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionResponse;
import com.smart_parking.smart_parking.entity.ParkingSession;
import com.smart_parking.smart_parking.entity.Reservation;
import com.smart_parking.smart_parking.repository.ParkingSessionRepository;
import com.smart_parking.smart_parking.repository.ReservationRepository;
import com.smart_parking.smart_parking.validator.ParkingSessionValidate;
import org.springframework.stereotype.Service;

import static com.smart_parking.smart_parking.enums.ParkingSessionStatus.ACTIVE;
import static com.smart_parking.smart_parking.enums.ParkingSessionStatus.COMPLETED;
import static com.smart_parking.smart_parking.enums.ReservationStatus.RESERVED;

@Service
public class ParkingSessionServiceImpl implements ParkingSessionService{
    private final ParkingSessionRepository parkingSessionRepository;
    private final ReservationRepository reservationRepository;
    public ParkingSessionServiceImpl(ParkingSessionRepository parkingSessionRepository, ReservationRepository reservationRepository)
    {
        this.parkingSessionRepository=parkingSessionRepository;
        this.reservationRepository=reservationRepository;
    }

    @Override
    public ParkingSessionResponse addParkingSession(ParkingSessionRequest parkingSessionRequest)
    {
        ParkingSessionValidate.parkingSessionValidate(parkingSessionRequest);
        Reservation reservation = reservationRepository.findById(parkingSessionRequest.getReservationId()).orElseThrow(()->new IllegalArgumentException("Reservation not found"));
        if(!reservation.getStatus().equals(RESERVED))
        {
            throw new IllegalArgumentException("Reservation must be reserved");
        }
        boolean isParkingSessionExists = parkingSessionRepository.existsByReservationId(parkingSessionRequest.getReservationId());
        if(isParkingSessionExists)
        {
            throw new IllegalArgumentException("Parking Session already exists");
        }
        if(parkingSessionRequest.getEntryTime().isBefore(reservation.getStartTime()) || parkingSessionRequest.getEntryTime().isAfter(reservation.getEndTime()))
        {
            throw new IllegalArgumentException("Parking Session entry time should be between reservation start and end time");
        }
        ParkingSession parkingSession=new ParkingSession();
        parkingSession.setReservation(reservation);
        parkingSession.setEntryTime(parkingSessionRequest.getEntryTime());
        parkingSession.setParkingSessionStatus(ACTIVE);

        ParkingSession newParkingSession = parkingSessionRepository.save(parkingSession);
        return new ParkingSessionResponse(true,"Parking Session Created Successfully",newParkingSession);
    }

    @Override
    public ParkingSessionResponse addExitTime(
            Long parkingSessionId,
            ParkingSessionExitRequest parkingSessionExitRequest) {

        if (parkingSessionExitRequest == null) {
            throw new IllegalArgumentException("Data is required");
        }

        if (parkingSessionExitRequest.getExitTime() == null) {
            throw new IllegalArgumentException("Exit time is required");
        }

        ParkingSession parkingSession =
                parkingSessionRepository.findById(parkingSessionId)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Parking session not found"));

        if (!parkingSession.getParkingSessionStatus().equals(ACTIVE)) {
            throw new IllegalArgumentException(
                    "Parking Session status should be active");
        }

        Reservation reservation = parkingSession.getReservation();

        if (parkingSessionExitRequest.getExitTime().isBefore(reservation.getStartTime())
                || parkingSessionExitRequest.getExitTime().isAfter(reservation.getEndTime())) {

            throw new IllegalArgumentException(
                    "Parking session Exit Time is not accordance to reservation starting and ending time");
        }

        if (parkingSessionExitRequest.getExitTime()
                .isBefore(parkingSession.getEntryTime())) {

            throw new IllegalArgumentException(
                    "Exit Time should be greater than Entry Time");
        }

        parkingSession.setExitTime(
                parkingSessionExitRequest.getExitTime());

        parkingSession.setParkingSessionStatus(COMPLETED);

        ParkingSession updatedParkingSession =
                parkingSessionRepository.save(parkingSession);

        return new ParkingSessionResponse(
                true,
                "Exit time updated successfully",
                updatedParkingSession);
    }
}
