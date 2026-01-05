package pl.edu.uws.pp.domain.dto.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Long invoiceId,
        BigDecimal amount,
        String transactionId
) {
}
