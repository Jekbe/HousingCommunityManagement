package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.invoice.*;
import pl.edu.uws.pp.domain.enums.InvoiceStatus;
import pl.edu.uws.pp.domain.enums.Role;
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
    public InvoiceShortResponse createInvoice(InvoiceRequest request,
                                              UserPrincipal principal) {
        var apartment = apartmentRepository.findById(request.apartmentId())
                .orElseThrow(() -> new NotFoundException("nie znaleziono mieszkania"));
        var user = principal.user();
        if (user.getManagerProfile().isNotManagingApartment(apartment)){
            throw new AccessDeniedException("Nie możesz wystawić opłaty dla tego mieszkania");
        }

        var invoice = InvoiceMapper.fromInvoiceRequest(request, apartment);
        var savedInvoice = invoiceRepository.save(invoice);

        return InvoiceMapper.toInvoiceShortResponse(savedInvoice);
    }

    @Override
    public InvoiceResponse getInvoiceInfo(Long id,
                                          UserPrincipal principal) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono opłaty"));
        var user = principal.user();
        if (user.isRoleEqualed(Role.BUILDING_MANAGER)
            && user.getManagerProfile().isNotManagingApartment(invoice.getApartment())){
            throw new AccessDeniedException("Nie masz dostępu do tej opłaty");
        }
        if (user.isRoleEqualed(Role.RESIDENT)
            && ! user.getResidentProfile().isOwningApartment(invoice.getApartment())) {
            throw new AccessDeniedException("Nie masz dostępu do tej opłaty");
        }

        return InvoiceMapper.toInvoiceResponse(invoice);
    }

    @Override
    @Transactional
    public InvoiceShortResponse editInvoice(Long id,
                                            InvoiceEditRequest request,
                                            UserPrincipal principal) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono opłaty"));
        var apartment = apartmentRepository.findById(request.apartmentId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));
        var user = principal.user();
        if (user.getManagerProfile().isNotManagingApartment(invoice.getApartment())) {
            throw new AccessDeniedException("Nie masz dostępu do tej opłaty");
        }
        if (user.getManagerProfile().isNotManagingApartment(apartment)) {
            throw new AccessDeniedException("Nie możesz wystawić opłaty na to mieszkanie");
        }

        invoice.setApartment(apartment);
        invoice.setDescription(request.description());
        invoice.setAmount(request.amount());
        invoice.setDueDate(request.dueDate());

        return InvoiceMapper.toInvoiceShortResponse(invoice);
    }

    @Override
    @Transactional
    public InvoiceShortResponse changeInvoiceStatus(Long id,
                                                    InvoiceChangeStatusRequest request,
                                                    UserPrincipal principal) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono opłaty"));
        var user = principal.user();
        if (user.getManagerProfile().isNotManagingApartment(invoice.getApartment())) {
            throw new AccessDeniedException("Nie masz dostępu d tego mieszkania");
        }

        if (! invoice.getStatus().canChangeTo(request.status()))
            throw new IllegalStateException("Nie można zmienić statusu");
        invoice.setStatus(request.status());

        return InvoiceMapper.toInvoiceShortResponse(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        var invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono opłaty"));
        if (invoice.getStatus().equals(InvoiceStatus.PAID)){
            throw new IllegalStateException("Nie możesz usunąć opłaconej opłaty");
        }
        invoiceRepository.delete(invoice);
    }
}
