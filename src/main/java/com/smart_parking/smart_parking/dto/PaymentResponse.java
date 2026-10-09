package com.smart_parking.smart_parking.dto;

import com.smart_parking.smart_parking.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private boolean status;
    private String message;
    private Payment payment;
}
