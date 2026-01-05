package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.file.FileRequest;
import pl.edu.uws.pp.domain.dto.file.FileResponse;
import pl.edu.uws.pp.domain.entity.File;
import pl.edu.uws.pp.domain.entity.User;

public class FileMapper {
    private FileMapper() {}

    public static File fromFileRequest(FileRequest request, User user) {
        return File.builder()
                .name(request.name())
                .fileType(request.type())
                .recipient(user)
                .build();
    }

    public static FileResponse toFileResponse(File file) {
        return FileResponse.builder()
                .fileId(file.getId())
                .Name(file.getName())
                .type(file.getFileType())
                .uploadTime(file.getUploadTime())
                .url(file.getFileUrl())
                .sender(UserMapper.toUserShortResponse(file.getSender()))
                .build();
    }
}
