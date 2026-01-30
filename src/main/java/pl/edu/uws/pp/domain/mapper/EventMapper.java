package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.event.EventRequest;
import pl.edu.uws.pp.domain.dto.event.EventResponse;
import pl.edu.uws.pp.domain.dto.event.EventShortResponse;
import pl.edu.uws.pp.domain.entity.Building;
import pl.edu.uws.pp.domain.entity.Event;
import pl.edu.uws.pp.domain.enums.EventStatus;

public class EventMapper {
    private EventMapper() {}

    public static Event fromEventRequest(EventRequest request, Building building) {
        return Event.builder()
                .building(building)
                .title(request.title())
                .description(request.description())
                .eventTime(request.eventTime())
                .status(EventStatus.PLANNED)
                .build();
    }

    public static EventShortResponse toEventShortResponse(Event event) {
        return EventShortResponse.builder()
                .eventId(event.getId())
                .Name(event.getTitle())
                .eventTime(event.getEventTime())
                .build();
    }

    public static EventResponse toEventResponse(Event event) {
        return EventResponse.builder()
                .eventId(event.getId())
                .building(BuildingMapper.toBuildingShortResponse(event.getBuilding()))
                .title(event.getTitle())
                .description(event.getDescription())
                .eventTime(event.getEventTime())
                .status(event.getStatus())
                .build();
    }
}
