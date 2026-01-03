package pl.edu.uws.pp.domain.dto.complaint;

import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;

public record ComplaintShortResponse(
        Long complaintId,
        UserShortResponse reporting,
        String description,
        FailureStatus status,
        LocalDateTime creationTime
) {
}
