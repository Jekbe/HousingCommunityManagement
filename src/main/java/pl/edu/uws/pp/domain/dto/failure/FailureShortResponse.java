package pl.edu.uws.pp.domain.dto.failure;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;

@Builder
public record FailureShortResponse(
        Long failureId,
        String description,
        FailureStatus status,
        LocalDateTime creationTime
) {
}
