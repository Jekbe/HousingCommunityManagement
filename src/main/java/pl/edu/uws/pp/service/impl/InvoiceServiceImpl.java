package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.domain.dto.invoice.*;
import pl.edu.uws.pp.domain.mapper.InvoiceMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.ApartmentRepository;
import pl.edu.uws.pp.repository.InvoiceRepository;
import pl.edu.uws.pp.service.InvoiceService;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ApartmentRepository apartmentRepository;

    @Override
    public InvoiceShortResponse createInvoice(InvoiceRequest request) {
        var apartment = apartmentRepository.findById(request.apartmentId())
                .orElseThrow(() -> new NotFoundException("nie znaleziono mieszkania"));
        var invoice = InvoiceMapper.fromInvoiceRequest(request, apartment);
        var savedInvoice = invoiceRepository.save(invoice);

        return InvoiceMapper.toInvoiceShortResponse(savedInvoice);
    }

    @Override
    public InvoiceResponse getInvoiceInfo(Long id) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono opłaty"));

        return InvoiceMapper.toInvoiceResponse(invoice);
    }

    @Override
    @Transactional
    public InvoiceShortResponse editInvoice(Long id,
                                            InvoiceEditRequest request) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono opłaty"));
        var apartment = apartmentRepository.findById(request.apartmentId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        invoice.setApartment(apartment);
        invoice.setDescription(request.description());
        invoice.setAmount(request.amount());
        invoice.setDueDate(request.dueDate());

        return InvoiceMapper.toInvoiceShortResponse(invoice);
    }

    @Override
    @Transactional
    public InvoiceShortResponse changeInvoiceStatus(Long id,
                                                    InvoiceChangeStatusRequest request) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono opłaty"));

        if (!invoice.getStatus().canChangeTo(request.status()))
            throw new IllegalStateException("Nie można zmienić statusu");
        invoice.setStatus(request.status());

        return InvoiceMapper.toInvoiceShortResponse(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono opłaty"));
        invoiceRepository.delete(invoice);
    }
}
