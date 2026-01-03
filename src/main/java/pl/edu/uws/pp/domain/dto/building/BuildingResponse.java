package pl.edu.uws.pp.domain.dto.building;

import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.dto.event.EventShortResponse;

import java.util.List;

public record BuildingResponse(
        Long buildingId,
        AddressResponse address,
        UserShortResponse manager,
        List<ApartmentShortResponse> apartmentsList,
        List<EventShortResponse> eventList
) {
}
