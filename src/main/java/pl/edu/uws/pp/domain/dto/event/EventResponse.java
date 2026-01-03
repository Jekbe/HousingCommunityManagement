package pl.edu.uws.pp.domain.dto.event;

import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.enums.EventStatus;

import java.time.LocalDateTime;

public record EventResponse(
        Long eventId,
        BuildingShortResponse building,
        String Title,
        String Description,
        LocalDateTime eventTime,
        EventStatus status
) {
}
