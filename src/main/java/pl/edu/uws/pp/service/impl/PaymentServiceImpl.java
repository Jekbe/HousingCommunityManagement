package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.payment.PaymentRequest;
import pl.edu.uws.pp.domain.dto.payment.PaymentResponse;
import pl.edu.uws.pp.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        return null;
    }

    @Override
    public PaymentResponse getPaymentInfo(Long id) {
        return null;
    }
}
