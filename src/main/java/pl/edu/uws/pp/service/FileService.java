package pl.edu.uws.pp.service;

import org.springframework.core.io.Resource;
import pl.edu.uws.pp.domain.dto.file.FileRequest;
import pl.edu.uws.pp.domain.dto.file.FileResponse;

import java.util.List;

public interface FileService {
    FileResponse uploadFile(FileRequest request);
    List<FileResponse> getUserFiles();
    Resource downloadFile(Long id);
    void deleteFile(Long id);
}
