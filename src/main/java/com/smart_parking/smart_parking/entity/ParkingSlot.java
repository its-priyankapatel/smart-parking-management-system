package com.smart_parking.smart_parking.entity;

import com.smart_parking.smart_parking.enums.SlotStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="parking_slots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slotNumber;
    @Enumerated(EnumType.STRING)
    private SlotStatus slotStatus=SlotStatus.AVAILABLE;
    @ManyToOne
    @JoinColumn(name = "parking_floor_id")
    private ParkingFloor parkingFloor;
}
