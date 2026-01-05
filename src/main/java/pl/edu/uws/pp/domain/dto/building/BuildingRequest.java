package pl.edu.uws.pp.domain.dto.building;

import lombok.Builder;

@Builder
public record BuildingRequest(
        String country,
        String city,
        String postalCode,
        String Street,
        String buildingNumber,
        Long managerId
) {
}
