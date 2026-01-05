package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.invoice.InvoiceRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceResponse;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceShortResponse;
import pl.edu.uws.pp.domain.entity.Apartment;
import pl.edu.uws.pp.domain.entity.Invoice;

public class InvoiceMapper {
    private InvoiceMapper() {}

    public static Invoice fromInvoiceRequest(InvoiceRequest request, Apartment apartment) {
        return Invoice.builder()
                .apartment(apartment)
                .description(request.description())
                .amount(request.amount())
                .dueDate(request.dueDate())
                .build();
    }

    public static InvoiceShortResponse toInvoiceShortResponse(Invoice invoice) {
        return InvoiceShortResponse.builder()
                .invoiceId(invoice.getId())
                .amount(invoice.getAmount())
                .status(invoice.getStatus())
                .dueDate(invoice.getDueDate())
                .build();
    }

    public static InvoiceResponse toInvoiceResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .invoiceId(invoice.getId())
                .apartment(ApartmentMapper.toApartmentShortResponse(invoice.getApartment()))
                .description(invoice.getDescription())
                .amount(invoice.getAmount())
                .status(invoice.getStatus())
                .dueDate(invoice.getDueDate())
                .creationTime(invoice.getCreatedAt())
                .payment(invoice.getPayment() != null
                        ? PaymentMapper.toPaymentShortResponse(invoice.getPayment())
                        : null)
                .build();
    }
}
