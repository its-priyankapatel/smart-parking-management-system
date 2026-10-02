package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.ReservationRequest;
import com.smart_parking.smart_parking.dto.ReservationResponse;
import com.smart_parking.smart_parking.entity.ParkingSlot;
import com.smart_parking.smart_parking.entity.Reservation;
import com.smart_parking.smart_parking.entity.User;
import com.smart_parking.smart_parking.entity.Vehicle;
import com.smart_parking.smart_parking.repository.ParkingSlotRepository;
import com.smart_parking.smart_parking.repository.ReservationRepository;
import com.smart_parking.smart_parking.repository.UserRepository;
import com.smart_parking.smart_parking.repository.VehicleRepository;
import com.smart_parking.smart_parking.validator.ReservationValidate;
import org.springframework.stereotype.Service;

import static com.smart_parking.smart_parking.enums.ReservationStatus.RESERVED;


@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final ParkingSlotRepository parkingSlotRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    public ReservationServiceImpl(ReservationRepository reservationRepository,ParkingSlotRepository parkingSlotRepository,UserRepository userRepository,VehicleRepository vehicleRepository)
    {
        this.reservationRepository=reservationRepository;
        this.parkingSlotRepository=parkingSlotRepository;
        this.userRepository = userRepository;
        this.vehicleRepository=vehicleRepository;
    }
    public ReservationResponse doReservation(ReservationRequest reservationRequest) {
        ReservationValidate.reservationValidate(reservationRequest);
        ParkingSlot parkingSlot = parkingSlotRepository.findById(reservationRequest.getParkingSlotId()).orElseThrow(()->new IllegalArgumentException("Parking slot not found"));
        boolean isReserved = reservationRepository.existsByParkingSlotIdAndStartTimeLessThanAndEndTimeGreaterThanAndStatus(parkingSlot.getId(), reservationRequest.getStartTime(),reservationRequest.getEndTime(),RESERVED);
        if(isReserved)
        {
            throw new IllegalArgumentException("Parking slot is already reserved for this time");
        }
        User user =  userRepository.findById(reservationRequest.getUserId()).orElseThrow(()->new IllegalArgumentException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(reservationRequest.getVehicleId()).orElseThrow(()->new IllegalArgumentException("Vehicle not found"));
        if(!vehicle.getUser().getId().equals(user.getId()))
        {
            throw new IllegalArgumentException("Vehicle does not belong to this user");
        }
        Reservation reservation=new Reservation();
        reservation.setUser(user);
        reservation.setVehicle(vehicle);
        reservation.setParkingSlot(parkingSlot);
        reservation.setStartTime(reservationRequest.getStartTime());
        reservation.setEndTime(reservationRequest.getEndTime());
        reservation.setStatus(RESERVED);
        Reservation newReservation = reservationRepository.save(reservation);
        return new ReservationResponse(true,"Reservation Created Successfully",newReservation);
    }
}
