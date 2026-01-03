package pl.edu.uws.pp.domain.dto.failure;

import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.enums.FailureStatus;

import java.time.LocalDateTime;
import java.util.List;

public record FailureResponse(
        Long failureId,
        UserShortResponse reporting,
        UserShortResponse manager,
        ApartmentShortResponse apartment,
        String description,
        List<PhotoResponse> photos,
        FailureStatus status,
        LocalDateTime creationTime
) {
}
