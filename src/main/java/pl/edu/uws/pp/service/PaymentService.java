package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.payment.PaymentRequest;
import pl.edu.uws.pp.domain.dto.payment.PaymentResponse;
import pl.edu.uws.pp.domain.dto.payment.PaymentShortResponse;

import java.util.List;

public interface PaymentService {
    PaymentShortResponse createPayment(PaymentRequest request);
    List<PaymentShortResponse> getPaymentsList();
    PaymentResponse getPaymentInfo(Long id);

}
