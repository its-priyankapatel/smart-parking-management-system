package com.smart_parking.smart_parking.service;


import com.smart_parking.smart_parking.dto.PaymentRequest;
import com.smart_parking.smart_parking.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse doPayment(PaymentRequest paymentRequest);
}
