package pl.edu.uws.pp.domain.dto.payment;

import lombok.Builder;

@Builder
public record PaymentShortResponse(
        Long paymentId,
        String transactionId
) {
}
