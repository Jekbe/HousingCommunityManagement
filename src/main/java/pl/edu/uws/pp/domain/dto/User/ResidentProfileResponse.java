package pl.edu.uws.pp.domain.dto.User;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintShortResponse;
import pl.edu.uws.pp.domain.dto.failure.FailureShortResponse;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceShortResponse;
import pl.edu.uws.pp.domain.dto.payment.PaymentShortResponse;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.List;

@Builder
public record ResidentProfileResponse(
        Long userId,
        Long profileId,
        String name,
        String surname,
        String pesel,
        String email,
        Role role,
        List<ApartmentShortResponse> apartments,
        List<FailureShortResponse> failures,
        List<InvoiceShortResponse> invoices,
        List<PaymentShortResponse> payments,
        List<ComplaintShortResponse> complaints
) {
}
