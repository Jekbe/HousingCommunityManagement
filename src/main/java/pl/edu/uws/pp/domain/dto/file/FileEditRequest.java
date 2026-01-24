package pl.edu.uws.pp.domain.dto.file;

import pl.edu.uws.pp.domain.enums.FileType;

public record FileEditRequest(
        String name,
        FileType type,
        Long recipientId
) {
}
