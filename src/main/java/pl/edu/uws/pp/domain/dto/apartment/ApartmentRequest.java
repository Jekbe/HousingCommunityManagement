package pl.edu.uws.pp.domain.dto.apartment;

public record ApartmentRequest(
        Long buildingId,
        int number
) {
}
