package pl.edu.uws.pp.domain.dto.file;

import lombok.Builder;
import pl.edu.uws.pp.domain.enums.FileType;

@Builder
public record FileRequest(
        String name,
        FileType type,
        Long recipientId
) {
}
