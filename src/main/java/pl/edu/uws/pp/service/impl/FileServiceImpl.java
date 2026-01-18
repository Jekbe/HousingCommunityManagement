package pl.edu.uws.pp.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.file.FileRequest;
import pl.edu.uws.pp.domain.dto.file.FileResponse;
import pl.edu.uws.pp.service.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public FileResponse uploadFile(FileRequest request,
                                   MultipartFile file) {
        return null;
    }

    @Override
    public List<FileResponse> getUserFiles(Long id) {
        return List.of();
    }

    @Override
    public Resource downloadFile(Long id) {
        return null;
    }

    @Override
    public void deleteFile(Long id) {

    }
}
