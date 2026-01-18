package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.invoice.InvoiceChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceEditRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceResponse;

public interface InvoiceService {
    InvoiceResponse createInvoice(InvoiceRequest request);
    InvoiceResponse getInvoiceInfo(Long id);
    InvoiceResponse editInvoice(Long id, InvoiceEditRequest request);
    InvoiceResponse changeInvoiceStatus(Long id, InvoiceChangeStatusRequest request);
    void deleteInvoice(Long id);
}
