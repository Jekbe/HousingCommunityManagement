package pl.edu.uws.pp.domain.dto.building;

public record BuildingRequest(
        String country,
        String city,
        String postalCode,
        String Street,
        String buildingNumber,
        Long ManagerId
) {
}
