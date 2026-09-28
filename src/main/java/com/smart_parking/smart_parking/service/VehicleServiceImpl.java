package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.VehicleRequest;
import com.smart_parking.smart_parking.dto.VehicleResponse;
import com.smart_parking.smart_parking.entity.User;
import com.smart_parking.smart_parking.entity.Vehicle;
import com.smart_parking.smart_parking.repository.UserRepository;
import com.smart_parking.smart_parking.repository.VehicleRepository;
import com.smart_parking.smart_parking.validator.VehicleValidate;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService{
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    public VehicleServiceImpl(VehicleRepository vehicleRepository,UserRepository userRepository)
    {
        this.vehicleRepository=vehicleRepository;
        this.userRepository=userRepository;
    }

    @Override
    public VehicleResponse registerVehicle(VehicleRequest vehicleRequest) {
        VehicleValidate.vehicleValidate(vehicleRequest);
        User user = userRepository.findById(vehicleRequest.getUserId()).orElseThrow(()->new IllegalArgumentException("User not found"));
        String vehicleNumber= vehicleRequest.getVehicleNumber().trim().toUpperCase();
        boolean isVehicleExists = vehicleRepository.existsByVehicleNumber(vehicleNumber);
        if(isVehicleExists)
        {
            throw new IllegalArgumentException("Vehicle already exists");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleRequest.getVehicleType());
        vehicle.setUser(user);
        Vehicle newVehicle = vehicleRepository.save(vehicle);
        return new VehicleResponse(true,"Vehicle Registered Successfully",newVehicle);
    }
}
