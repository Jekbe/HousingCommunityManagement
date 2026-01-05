package pl.edu.uws.pp.domain.dto.invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceRequest(
        Long apartmentId,
        String description,
        BigDecimal amount,
        LocalDateTime dueDate
) {
}
