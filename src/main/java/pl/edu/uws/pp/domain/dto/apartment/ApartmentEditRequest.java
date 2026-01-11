package pl.edu.uws.pp.domain.dto.apartment;

public record ApartmentEditRequest(
        Long apartmentId,
        String number
) {
}
