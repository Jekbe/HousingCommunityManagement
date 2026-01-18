package pl.edu.uws.pp.domain.dto.invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceEditRequest(
        Long apartmentId,
        String description,
        BigDecimal amount,
        LocalDateTime dueDate
) {
}
