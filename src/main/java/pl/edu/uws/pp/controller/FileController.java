package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.file.FileRequest;
import pl.edu.uws.pp.domain.dto.file.FileResponse;
import pl.edu.uws.pp.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileResponse uploadFile(@RequestPart("data") FileRequest request,
                                   @RequestPart("file") MultipartFile file){
        return fileService.uploadFile(request, file);
    }

    @GetMapping("/user/{id}")
    public List<FileResponse> filesList(@PathVariable Long id){
        return fileService.getUserFiles(id);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id){
        var file = fileService.downloadFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
    }
}
