package pl.edu.uws.pp.domain.dto.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventShortResponse(
        Long eventId,
        String title,
        LocalDateTime eventTime
) {
}
