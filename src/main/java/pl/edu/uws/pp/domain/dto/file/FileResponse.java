package pl.edu.uws.pp.domain.dto.file;

import pl.edu.uws.pp.domain.dto.User.UserShortResponse;
import pl.edu.uws.pp.domain.enums.FileType;

import java.time.LocalDateTime;

public record FileResponse(
        Long fileId,
        String Name,
        FileType type,
        LocalDateTime uploadTime,
        String url,
        UserShortResponse sender
) {
}
