package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ParkingSessionRequest;
import com.smart_parking.smart_parking.dto.ParkingSessionResponse;
import com.smart_parking.smart_parking.entity.ParkingSession;
import com.smart_parking.smart_parking.entity.Reservation;
import com.smart_parking.smart_parking.repository.ParkingSessionRepository;
import com.smart_parking.smart_parking.repository.ReservationRepository;
import com.smart_parking.smart_parking.validator.ParkingSessionValidate;
import org.springframework.stereotype.Service;

import static com.smart_parking.smart_parking.enums.ParkingSessionStatus.ACTIVE;
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
        System.out.println("Entry Time: " + parkingSessionRequest.getEntryTime());
        System.out.println("Start Time: " + reservation.getStartTime());
        System.out.println("End Time: " + reservation.getEndTime());
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
}
