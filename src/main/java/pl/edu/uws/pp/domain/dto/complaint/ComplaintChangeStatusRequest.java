package pl.edu.uws.pp.domain.dto.complaint;

import pl.edu.uws.pp.domain.enums.FailureStatus;

public record ComplaintChangeStatusRequest(
        FailureStatus status
) {
}
