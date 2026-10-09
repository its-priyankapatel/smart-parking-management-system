package com.smart_parking.smart_parking.service;

import com.smart_parking.smart_parking.dto.PaymentRequest;
import com.smart_parking.smart_parking.dto.PaymentResponse;
import com.smart_parking.smart_parking.entity.ParkingSession;
import com.smart_parking.smart_parking.entity.Payment;
import com.smart_parking.smart_parking.enums.PaymentStatus;
import com.smart_parking.smart_parking.repository.ParkingSessionRepository;
import com.smart_parking.smart_parking.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;

import static com.smart_parking.smart_parking.enums.ParkingSessionStatus.COMPLETED;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final ParkingSessionRepository parkingSessionRepository;
    public PaymentServiceImpl(PaymentRepository paymentRepository,ParkingSessionRepository parkingSessionRepository)
    {
        this.paymentRepository=paymentRepository;
        this.parkingSessionRepository=parkingSessionRepository;
    }
    @Override
    public PaymentResponse doPayment(PaymentRequest paymentRequest)
    {
      if(paymentRequest==null)
      {
          throw new IllegalArgumentException("Payment data is required");
      }
      if(paymentRequest.getParkingSessionId()==null)
      {
          throw new IllegalArgumentException("Payment session Id is required");
      }
      ParkingSession parkingSession = parkingSessionRepository.findById(paymentRequest.getParkingSessionId()).orElseThrow(()->new IllegalArgumentException("Parking session does not exist"));

      if(!parkingSession.getParkingSessionStatus().equals(COMPLETED))
      {
          throw new IllegalArgumentException("Parking session status should be completed");
      }
      boolean isPaymentExists= paymentRepository.existsByParkingSessionId(paymentRequest.getParkingSessionId());
      if(isPaymentExists)
      {
          throw new IllegalArgumentException("Payment already exists");
      }
      Duration duration = Duration.between(parkingSession.getEntryTime(),parkingSession.getExitTime());
      long totalMinutes = duration.toMinutes();
      long hours = totalMinutes/60;
      if(totalMinutes % 60 !=0)
      {
          hours++;
      }
      BigDecimal amount = BigDecimal.valueOf(hours).multiply(BigDecimal.valueOf(20));
      Payment payment = new Payment();
      payment.setParkingSession(parkingSession);
      payment.setPaymentStatus(PaymentStatus.PAID);
      payment.setAmount(amount);

      Payment newPayment = paymentRepository.save(payment);
      return new PaymentResponse(true,"Payment completed successfully",newPayment);
    }
}
