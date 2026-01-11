package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.invoice.InvoiceChangeStatusStatusRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceEditRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceResponse;

public interface InvoiceService {
    InvoiceResponse createInvoice(InvoiceRequest request);
    InvoiceResponse getInvoiceInfo(Long id);
    InvoiceResponse editInvoice(InvoiceEditRequest request);
    InvoiceResponse changeInvoiceStatus(InvoiceChangeStatusStatusRequest request);
    void deleteInvoice(Long id);
}
