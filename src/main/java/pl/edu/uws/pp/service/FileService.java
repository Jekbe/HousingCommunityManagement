package pl.edu.uws.pp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.file.*;

import java.util.List;

public interface FileService {
    FileResponse uploadFile(FileRequest request, MultipartFile file);
    List<FileResponse> getUserFiles(Long id);
    Resource downloadFile(Long id);
    void deleteFile(Long id);
}
