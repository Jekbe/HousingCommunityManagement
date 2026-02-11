package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.file.FileRequest;
import pl.edu.uws.pp.domain.dto.file.FileResponse;
import pl.edu.uws.pp.domain.entity.File;
import pl.edu.uws.pp.domain.entity.User;

public class FileMapper {
    private FileMapper() {}

    public static File fromFileRequest(FileRequest request, String url, User recipient, User sender) {
        return File.builder()
                .name(request.name())
                .fileType(request.type())
                .fileUrl(url)
                .sender(sender)
                .recipient(recipient)
                .build();
    }

    public static FileResponse toFileResponse(File file) {
        return FileResponse.builder()
                .fileId(file.getId())
                .name(file.getName())
                .type(file.getFileType())
                .uploadTime(file.getUploadTime())
                .sender(UserMapper.toUserShortResponse(file.getSender()))
                .recipient(UserMapper.toUserShortResponse(file.getRecipient()))
                .build();
    }
}
