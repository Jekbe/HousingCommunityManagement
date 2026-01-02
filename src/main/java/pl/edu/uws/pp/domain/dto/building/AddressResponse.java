package pl.edu.uws.pp.domain.dto.building;

public record AddressResponse(
        String country,
        String city,
        String postalCode,
        String Street,
        String buildingNumber
) {
}
