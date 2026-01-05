package pl.edu.uws.pp.domain.dto.message;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.User.UserShortResponse;

import java.time.LocalDateTime;

@Builder
public record MessageResponse(
    Long messageId,
    UserShortResponse sender,
    String subject,
    String content,
    LocalDateTime sendTime
) {
}
