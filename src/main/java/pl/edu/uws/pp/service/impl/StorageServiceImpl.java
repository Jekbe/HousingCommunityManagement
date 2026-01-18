package pl.edu.uws.pp.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {
    @Override
    public String saveFile(String name,
                           MultipartFile file) {
        return "";
    }

    @Override
    public Resource downloadFile(String url) {
        return null;
    }

    @Override
    public void deleteFile(String url) {

    }
}
