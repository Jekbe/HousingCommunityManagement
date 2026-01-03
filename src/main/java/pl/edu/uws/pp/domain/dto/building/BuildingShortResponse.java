package pl.edu.uws.pp.domain.dto.building;

public record BuildingShortResponse(
        Long buildingID,
        AddressResponse address
) {
}
