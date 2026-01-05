package pl.edu.uws.pp.domain.dto.complaint;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;

@Builder
public record ComplaintShortResponse(
        Long complaintId,
        String description,
        FailureStatus status,
        LocalDateTime creationTime
) {
}
