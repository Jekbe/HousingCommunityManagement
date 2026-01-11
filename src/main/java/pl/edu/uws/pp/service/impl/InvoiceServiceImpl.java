package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceChangeStatusStatusRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceEditRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceRequest;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceResponse;
import pl.edu.uws.pp.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public InvoiceResponse createInvoice(InvoiceRequest request) {
        return null;
    }

    @Override
    public InvoiceResponse getInvoiceInfo(Long id) {
        return null;
    }

    @Override
    public InvoiceResponse editInvoice(Long id, InvoiceEditRequest request) {
        return null;
    }

    @Override
    public InvoiceResponse changeInvoiceStatus(Long id, InvoiceChangeStatusStatusRequest request) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }
}
