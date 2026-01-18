package pl.edu.uws.pp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String saveFile(String name, MultipartFile file);
    Resource downloadFile(String url);
    void deleteFile(String url);
}
