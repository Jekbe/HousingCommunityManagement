package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.file.FileEditRequest;
import pl.edu.uws.pp.domain.dto.file.FileRequest;
import pl.edu.uws.pp.domain.dto.file.FileResponse;
import pl.edu.uws.pp.domain.entity.File;
import pl.edu.uws.pp.domain.mapper.FileMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.FileRepository;
import pl.edu.uws.pp.repository.UserRepository;
import pl.edu.uws.pp.service.FileService;
import pl.edu.uws.pp.service.StorageService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;

    @Override
    @Transactional
    public FileResponse uploadFile(FileRequest request,
                                   MultipartFile file,
                                   UserPrincipal principal) {
        var recipient = userRepository.findById(request.recipientId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var sender = principal.user();

        var url = storageService.saveFile(request.name(), file);
        var fileEntity = FileMapper.fromFileRequest(request, url, recipient, sender);
        var savedFile = fileRepository.save(fileEntity);

        return FileMapper.toFileResponse(savedFile);
    }

    @Override
    public List<FileResponse> getUserFiles(Long id,
                                           UserPrincipal principal) {
        var fileList = new ArrayList<File>();

        var loggedUser = principal.user();
        if (loggedUser.getId().equals(id)) {
            fileList.addAll(loggedUser.getReceivedFiles());
            fileList.addAll(loggedUser.getSendFiles());
        } else {
            var user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));

            fileList.addAll(loggedUser.getReceivedFiles()
                    .stream()
                    .filter(file -> file.getSender().equals(user))
                    .toList());
            fileList.addAll(loggedUser.getSendFiles()
                    .stream()
                    .filter(file -> file.getRecipient().equals(user))
                    .toList());
        }

        return fileList.stream()
                .map(FileMapper::toFileResponse)
                .toList();
    }

    @Override
    @Transactional
    public FileResponse editFileData(Long id,
                                     FileEditRequest request,
                                     UserPrincipal principal) {
        var file =  fileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono pliku"));
        var user = userRepository.findById(request.recipientId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var loggedUser = principal.user();
        if (! file.getSender().equals(loggedUser)) {
            throw new AccessDeniedException("Nie jesteś właścicielem pliku");
        }

        file.setName(request.name());
        file.setFileType(request.type());
        file.setRecipient(user);

        return FileMapper.toFileResponse(file);
    }

    @Override
    public Resource downloadFile(Long id,
                                 UserPrincipal principal) {
        var file = fileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono pliku"));
        var user = principal.user();
        if (! file.getRecipient().equals(user)
                && ! file.getSender().equals(user)) {
            throw new AccessDeniedException("Nie masz dostępu do tego pliku");
        }

        return storageService.downloadFile(file.getFileUrl());
    }

    @Override
    public void deleteFile(Long id,
                           UserPrincipal principal) {
        var file = fileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono pliku"));
        var user = principal.user();
        if (! file.getSender().equals(user)) {
            throw new AccessDeniedException("Nie jesteś właścicielem tego pliku");
        }

        storageService.deleteFile(file.getFileUrl());
        fileRepository.delete(file);
    }
}
