package pl.edu.uws.pp.domain.dto.payment;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceShortResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentResponse(
    Long paymentId,
    InvoiceShortResponse invoice,
    BigDecimal amount,
    LocalDateTime paymentTime,
    String transactionId
) {
}
