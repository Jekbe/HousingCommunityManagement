package pl.edu.uws.pp.domain.dto.building;

import lombok.Builder;

@Builder
public record AddressResponse(
        String country,
        String city,
        String postalCode,
        String street,
        String buildingNumber
) {
}
