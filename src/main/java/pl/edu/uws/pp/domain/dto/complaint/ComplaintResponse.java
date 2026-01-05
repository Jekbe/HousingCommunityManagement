package pl.edu.uws.pp.domain.dto.complaint;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;

@Builder
public record ComplaintResponse(
        Long complaintId,
        UserShortResponse reporting,
        UserShortResponse manager,
        String description,
        FailureStatus status,
        LocalDateTime creationTime
) {
}
