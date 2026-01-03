package pl.edu.uws.pp.domain.dto.payment;

import pl.edu.uws.pp.domain.dto.invoice.InvoiceShortResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
    Long paymentId,
    InvoiceShortResponse invoice,
    BigDecimal amount,
    LocalDateTime paymentTime,
    String transactionId
) {
}
