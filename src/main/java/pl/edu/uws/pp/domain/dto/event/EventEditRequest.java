package pl.edu.uws.pp.domain.dto.event;

import java.time.LocalDateTime;

public record EventEditRequest(
        Long eventId,
        Long buildingId,
        String tittle,
        String description,
        LocalDateTime eventTime
) {
}
