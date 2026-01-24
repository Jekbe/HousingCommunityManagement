package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.invoice.*;

public interface InvoiceService {
    InvoiceShortResponse createInvoice(InvoiceRequest request);
    InvoiceResponse getInvoiceInfo(Long id);
    InvoiceShortResponse editInvoice(Long id, InvoiceEditRequest request);
    InvoiceShortResponse changeInvoiceStatus(Long id, InvoiceChangeStatusRequest request);
    void deleteInvoice(Long id);
}
