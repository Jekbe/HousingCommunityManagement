package pl.edu.uws.pp.service;

import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.invoice.*;

public interface InvoiceService {
    InvoiceShortResponse createInvoice(InvoiceRequest request, UserPrincipal principal);
    InvoiceResponse getInvoiceInfo(Long id, UserPrincipal principal);
    InvoiceShortResponse editInvoice(Long id, InvoiceEditRequest request, UserPrincipal principal);
    InvoiceShortResponse changeInvoiceStatus(Long id, InvoiceChangeStatusRequest request, UserPrincipal principal);
    void deleteInvoice(Long id);
}
