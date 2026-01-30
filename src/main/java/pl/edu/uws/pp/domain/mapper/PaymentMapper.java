package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.payment.PaymentRequest;
import pl.edu.uws.pp.domain.dto.payment.PaymentResponse;
import pl.edu.uws.pp.domain.dto.payment.PaymentShortResponse;
import pl.edu.uws.pp.domain.entity.Invoice;
import pl.edu.uws.pp.domain.entity.Payment;
import pl.edu.uws.pp.domain.entity.Resident;

public class PaymentMapper {
    private PaymentMapper() {}

    public static Payment fromPaymentRequest(PaymentRequest request, Invoice invoice, Resident resident) {
        return Payment.builder()
                .invoice(invoice)
                .resident(resident)
                .amount(request.amount())
                .transactionId(request.transactionId())
                .build();
    }

    public static PaymentShortResponse toPaymentShortResponse(Payment payment) {
        return PaymentShortResponse.builder()
                .paymentId(payment.getId())
                .transactionId(payment.getTransactionId())
                .build();
    }

    public static PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .invoice(InvoiceMapper.toInvoiceShortResponse(payment.getInvoice()))
                .amount(payment.getAmount())
                .paymentTime(payment.getPaidAt())
                .transactionId(payment.getTransactionId())
                .build();
    }
}
