package pl.edu.uws.pp.domain.dto.building;

import pl.edu.uws.pp.domain.dto.User.ManagerShortResponse;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.dto.event.EventShortResponse;

import java.util.List;

public record BuildingResponse(
        Long buildingId,
        AddressResponse address,
        ManagerShortResponse manager,
        List<ApartmentShortResponse> apartmentsList,
        List<EventShortResponse> eventList
) {
}
