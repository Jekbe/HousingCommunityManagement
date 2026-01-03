package pl.edu.uws.pp.domain.dto.failure;

import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;

public record FailureShortResponse(
        Long failureId,
        UserShortResponse userShortResponse,
        String description,
        FailureStatus status,
        LocalDateTime creationTime
) {
}
