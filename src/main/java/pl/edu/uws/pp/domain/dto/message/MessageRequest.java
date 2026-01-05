package pl.edu.uws.pp.domain.dto.message;

import lombok.Builder;

@Builder
public record MessageRequest(
        Long recipientId,
        String subject,
        String content
) {
}
