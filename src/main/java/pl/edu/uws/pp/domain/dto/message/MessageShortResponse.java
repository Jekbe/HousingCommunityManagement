package pl.edu.uws.pp.domain.dto.message;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.User.UserShortResponse;

import java.time.LocalDateTime;

@Builder
public record MessageShortResponse(
        Long messageId,
        String subject,
        LocalDateTime sendingTime
) {
}
