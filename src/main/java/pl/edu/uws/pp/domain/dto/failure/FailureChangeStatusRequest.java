package pl.edu.uws.pp.domain.dto.failure;

import pl.edu.uws.pp.domain.enums.FailureStatus;

public record FailureChangeStatusRequest(
        FailureStatus status
) {
}
