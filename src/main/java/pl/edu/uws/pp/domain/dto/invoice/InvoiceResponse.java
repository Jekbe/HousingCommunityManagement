package pl.edu.uws.pp.domain.dto.invoice;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.dto.payment.PaymentShortResponse;
import pl.edu.uws.pp.domain.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record InvoiceResponse(
        Long invoiceId,
        ApartmentShortResponse apartment,
        String description,
        BigDecimal amount,
        InvoiceStatus status,
        LocalDateTime dueDate,
        LocalDateTime creationTime,
        PaymentShortResponse payment
) {
}
