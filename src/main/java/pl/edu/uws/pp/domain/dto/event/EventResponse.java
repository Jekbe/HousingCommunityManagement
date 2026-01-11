package pl.edu.uws.pp.domain.dto.event;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.enums.EventStatus;

import java.time.LocalDateTime;

@Builder
public record EventResponse(
        Long eventId,
        BuildingShortResponse building,
        String title,
        String description,
        LocalDateTime eventTime,
        EventStatus status
) {
}
