package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.file.*;
import pl.edu.uws.pp.service.FileService;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileResponse uploadFile(@RequestPart("data") FileRequest request,
                                   @RequestPart("file") MultipartFile file,
                                   @AuthenticationPrincipal UserPrincipal user) {
        return fileService.uploadFile(request, file, user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{id}")
    public List<FileResponse> filesList(@PathVariable Long id,
                                        @AuthenticationPrincipal UserPrincipal user) {
        return fileService.getUserFiles(id, user);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @PutMapping("/{id}")
    public FileResponse editFile(@PathVariable Long id,
                                 @RequestBody FileEditRequest request,
                                 @AuthenticationPrincipal UserPrincipal user) {
        return fileService.editFileData(id, request, user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id,
                                                 @AuthenticationPrincipal UserPrincipal user) {
        var file = fileService.downloadFile(id, user);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id,
                           @AuthenticationPrincipal UserPrincipal user) {
        fileService.deleteFile(id, user);
    }
}
