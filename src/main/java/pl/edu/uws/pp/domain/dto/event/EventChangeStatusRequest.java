package pl.edu.uws.pp.domain.dto.event;

import pl.edu.uws.pp.domain.enums.EventStatus;

public record EventChangeStatusRequest(
        Long eventId,
        EventStatus status
) {
}
