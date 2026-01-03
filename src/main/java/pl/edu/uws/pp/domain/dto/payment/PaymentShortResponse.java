package pl.edu.uws.pp.domain.dto.payment;

public record PaymentShortResponse(
        Long paymentId,
        String transactionId
) {
}
