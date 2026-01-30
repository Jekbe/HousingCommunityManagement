package pl.edu.uws.pp.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.service.StorageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    private final static Path FILES_PATH = Paths.get("./src/main/resources/files");

    public StorageServiceImpl() throws IOException {
        if (! Files.exists(FILES_PATH)) {
            Files.createDirectories(FILES_PATH);
        }
    }

    @Override
    public String saveFile(String name,
                           MultipartFile file) {
        try {
            var uniqueName = UUID.randomUUID() + "_" + name;
            var target = FILES_PATH.resolve(uniqueName);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return target.toString();
        }  catch (IOException e) {
            throw new RuntimeException("Nie udało się zapisać pliku", e);
        }
    }

    @Override
    public Resource downloadFile(String url) {
        try{
            Path file = Paths.get(url);
            if (! Files.exists(file)) {
                throw new RuntimeException("Nie znaleziono pliku");
            }
            return new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Nie prawidłowy url pliku", e);
        }
    }

    @Override
    public void deleteFile(String url) {
        try {
            Path file = Paths.get(url);
            if (Files.exists(file)) {
                Files.delete(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Nie udało się usunąć pliku", e);
        }
    }
}
