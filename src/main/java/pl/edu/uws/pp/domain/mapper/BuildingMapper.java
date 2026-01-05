package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.building.AddressResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.entity.Address;
import pl.edu.uws.pp.domain.entity.Building;
import pl.edu.uws.pp.domain.entity.Manager;

public class BuildingMapper {
    private BuildingMapper() {}

    public static Building fromAddressAndManagerCreateBuilding(Address address, Manager manager) {
        return Building.builder()
                .address(address)
                .manager(manager)
                .build();
    }

    public static Address fromBuildingRequestCreateAddress(BuildingRequest request) {
        return Address.builder()
                .country(request.country())
                .city(request.city())
                .postalCode(request.postalCode())
                .street(request.Street())
                .buildingNumber(request.buildingNumber())
                .build();
    }

    public static BuildingShortResponse toBuildingShortResponse(Building building) {
        return BuildingShortResponse.builder()
                .buildingID(building.getId())
                .address(toAddressResponse(building.getAddress()))
                .build();
    }

    public static BuildingResponse toBuildingResponse(Building building) {
        return BuildingResponse.builder()
                .buildingId(building.getId())
                .address(toAddressResponse(building.getAddress()))
                .manager(UserMapper.toUserShortResponse(building.getManager()))
                .apartmentsList(building.getApartments()
                        .stream()
                        .map(ApartmentMapper::toApartmentShortResponse)
                        .toList())
                .eventList(building.getEvents()
                        .stream()
                        .map(EventMapper::toEventShortResponse)
                        .toList())
                .build();
    }

    private static AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .Street(address.getStreet())
                .buildingNumber(address.getBuildingNumber())
                .build();
    }
}
