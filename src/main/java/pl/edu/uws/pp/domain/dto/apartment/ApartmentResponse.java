package pl.edu.uws.pp.domain.dto.apartment;

import pl.edu.uws.pp.domain.dto.failure.FailureShortResponse;
import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceShortResponse;

import java.util.List;

public record ApartmentResponse(
        Long apartmentId,
        BuildingShortResponse building,
        int number,
        List<UserShortResponse> userInfo,
        List<InvoiceShortResponse> invoices,
        List<FailureShortResponse> failures
) {
}
