package pl.edu.uws.pp.domain.dto.invoice;

import pl.edu.uws.pp.domain.enums.InvoiceStatus;

public record InvoiceChangeStatusStatusRequest(
        Long invoiceId,
        InvoiceStatus status
) {
}
