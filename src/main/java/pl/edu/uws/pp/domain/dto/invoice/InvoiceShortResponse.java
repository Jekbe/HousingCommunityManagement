package pl.edu.uws.pp.domain.dto.invoice;

import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceShortResponse(
        Long invoiceId,
        ApartmentShortResponse apartment,
        BuildingShortResponse building,
        BigDecimal amount,
        InvoiceStatus status,
        LocalDateTime dueDate
) {
}
