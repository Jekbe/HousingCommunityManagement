package pl.edu.uws.pp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.file.*;

import java.util.List;

public interface FileService {
    FileResponse uploadFile(FileRequest request, MultipartFile file, UserPrincipal principal);
    List<FileResponse> getUserFiles(Long id, UserPrincipal principal);
    FileResponse editFileData(Long id, FileEditRequest request, UserPrincipal principal);
    Resource downloadFile(Long id, UserPrincipal principal);
    void deleteFile(Long id, UserPrincipal principal);
}
