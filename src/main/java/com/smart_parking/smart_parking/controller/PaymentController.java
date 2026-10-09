package com.smart_parking.smart_parking.controller;

import com.smart_parking.smart_parking.dto.PaymentRequest;
import com.smart_parking.smart_parking.dto.PaymentResponse;
import com.smart_parking.smart_parking.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
         private final PaymentService paymentService;
         public PaymentController(PaymentService paymentService)
         {
             this.paymentService=paymentService;
         }

         @PostMapping
         public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest)
         {
             PaymentResponse response = paymentService.doPayment(paymentRequest);
             return ResponseEntity.status(HttpStatus.CREATED).body(response);
         }
}
