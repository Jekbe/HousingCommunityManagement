package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.payment.PaymentRequest;
import pl.edu.uws.pp.domain.dto.payment.PaymentResponse;
import pl.edu.uws.pp.domain.dto.payment.PaymentShortResponse;
import pl.edu.uws.pp.domain.entity.Payment;
import pl.edu.uws.pp.domain.enums.InvoiceStatus;
import pl.edu.uws.pp.domain.enums.Role;
import pl.edu.uws.pp.domain.mapper.PaymentMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.InvoiceRepository;
import pl.edu.uws.pp.repository.PaymentRepository;
import pl.edu.uws.pp.service.PaymentService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public PaymentShortResponse createPayment(PaymentRequest request,
                                              UserPrincipal principal) {
        var invoice = invoiceRepository.findById(request.invoiceId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono opłaty"));
        var user = principal.user();
        if (! user.getResidentProfile()
                .isOwningApartment(invoice.getApartment())) {
            throw new AccessDeniedException("Nie możesz opłacić tej opłaty");
        }
        var payment = PaymentMapper.fromPaymentRequest(request, invoice, user.getResidentProfile());
        var amountToPay = invoice.getAmount();


        if (invoice.getStatus() == InvoiceStatus.PAID || invoice.getStatus() == InvoiceStatus.CANCELLED)
            throw new IllegalStateException("Faktura jest już opłacona");

        if (invoice.getStatus() == InvoiceStatus.EXPIRED) {
            long daysBetween = ChronoUnit.DAYS.between(invoice.getDueDate()
                    .toLocalDate(), LocalDateTime.now());

            amountToPay = amountToPay.multiply(BigDecimal.valueOf(1)
                        .add(BigDecimal.valueOf(11)
                                .divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_UP))
                    .pow((int) daysBetween));
        }

        if (amountToPay.subtract(request.amount())
                .abs()
                .compareTo(BigDecimal.valueOf(0.05)) > 0)
            throw new IllegalStateException("Zapłacono nie pełną kwotę");

        var savedPayment = paymentRepository.save(payment);
        invoice.setStatus(InvoiceStatus.PAID);

        return PaymentMapper.toPaymentShortResponse(savedPayment);
    }

    @Override
    public List<PaymentShortResponse> getPaymentsList(UserPrincipal principal) {
        List<Payment> payments;
        var user = principal.user();
        if (user.isRoleEqualed(Role.BUILDING_MANAGER)){
            payments = paymentRepository.findAllByInvoice_Apartment_Building_Manager(user.getManagerProfile());
        } else if (user.isRoleEqualed(Role.RESIDENT)){
            payments = paymentRepository.findAllByInvoice_Apartment_ResidentsContains(user.getResidentProfile());
        } else {
            payments = paymentRepository.findAll();
        }

        return payments.stream()
                .map(PaymentMapper::toPaymentShortResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentInfo(Long id,
                                          UserPrincipal principal) {
        var payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono płatności"));
        var user = principal.user();
        if (user.isRoleEqualed(Role.BUILDING_MANAGER)
            && user.getManagerProfile().isNotManagingApartment(payment.getInvoice().getApartment())){
            throw new AccessDeniedException("Nie masz dostępu do tej płatności");
        }
        if (user.isRoleEqualed(Role.RESIDENT)
            && ! user.getResidentProfile().isOwningApartment(payment.getInvoice().getApartment())){
            throw new AccessDeniedException("Nie masz dostępu do tej płatności");
        }

        return PaymentMapper.toPaymentResponse(payment);
    }
}
