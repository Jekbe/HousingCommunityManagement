package pl.edu.uws.pp.domain.dto.event;

import java.time.LocalDateTime;

public record EventRequest(
        Long BuildingId,
        String title,
        String description,
        LocalDateTime eventTime
) {
}
