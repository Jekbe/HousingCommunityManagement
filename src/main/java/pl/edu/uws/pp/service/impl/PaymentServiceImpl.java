package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.payment.PaymentRequest;
import pl.edu.uws.pp.domain.dto.payment.PaymentResponse;
import pl.edu.uws.pp.domain.dto.payment.PaymentShortResponse;
import pl.edu.uws.pp.service.PaymentService;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        return null;
    }

    @Override
    public List<PaymentShortResponse> getPaymentsList() {
        return List.of();
    }

    @Override
    public PaymentResponse getPaymentInfo(Long id) {
        return null;
    }
}
