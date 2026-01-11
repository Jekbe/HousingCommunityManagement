package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.payment.PaymentRequest;
import pl.edu.uws.pp.domain.dto.payment.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse getPaymentInfo(Long id);

}
