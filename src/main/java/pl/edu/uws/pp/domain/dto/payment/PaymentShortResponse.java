package pl.edu.uws.pp.domain.dto.payment;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentShortResponse(
        Long paymentId,
        BigDecimal amount,
        LocalDateTime paymentTime
) {
}
