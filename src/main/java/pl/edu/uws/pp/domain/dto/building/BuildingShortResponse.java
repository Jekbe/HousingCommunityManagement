package pl.edu.uws.pp.domain.dto.building;

import lombok.Builder;

@Builder
public record BuildingShortResponse(
        Long buildingID,
        AddressResponse address
) {
}
