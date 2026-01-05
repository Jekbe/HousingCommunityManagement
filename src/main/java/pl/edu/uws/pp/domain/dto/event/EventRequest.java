package pl.edu.uws.pp.domain.dto.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventRequest(
        Long BuildingId,
        String title,
        String description,
        LocalDateTime eventTime
) {
}
