package pl.edu.uws.pp.domain.dto.message;

public record MessageRequest(
        Long recipientId,
        String subject,
        String content
) {
}
