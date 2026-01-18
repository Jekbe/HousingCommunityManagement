package pl.edu.uws.pp.domain.dto.building;

public record BuildingEditRequest(
        String country,
        String postalCode,
        String city,
        String street,
        String buildingNumber,
        Long managerId
) {
}
