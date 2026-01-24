package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.failure.*;
import pl.edu.uws.pp.domain.entity.Failure;
import pl.edu.uws.pp.domain.entity.Photo;
import pl.edu.uws.pp.domain.mapper.FailureMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.ApartmentRepository;
import pl.edu.uws.pp.repository.FailureRepository;
import pl.edu.uws.pp.repository.PhotoRepository;
import pl.edu.uws.pp.service.FailureService;
import pl.edu.uws.pp.service.StorageService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FailureServiceImpl implements FailureService {
    private final FailureRepository failureRepository;
    private final PhotoRepository photoRepository;
    private final ApartmentRepository apartmentRepository;
    private final StorageService storageService;

    @Override
    @Transactional
    public FailureShortResponse createFailure(FailureRequest request,
                                              List<MultipartFile> photos) {
        var apartment = apartmentRepository.findById(request.apartmentId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono Mieszkania"));
        var failure = FailureMapper.fromFailureRequest(request, apartment);
        var savedFailure = failureRepository.save(failure);

        if (photos != null && !photos.isEmpty()) {
            var photosEntities = createPhotosList(photos, savedFailure);
            savedFailure.setPhotos(photosEntities);
        }

        return FailureMapper.toFailureShortResponse(savedFailure);
    }

    @Override
    public FailureResponse getFailureInfo(Long failureId) {
        var failure = failureRepository.findById(failureId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono awarii"));

        return FailureMapper.toFailureResponse(failure);
    }

    @Override
    @Transactional
    public FailureShortResponse editFailure(Long id,
                                            FailureEditRequest request,
                                            List<MultipartFile> photos) {
        var failure = failureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono awarii"));
        var apartment = apartmentRepository.findById(request.apartmentId())
                        .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        if (!failure.getApartment()
                .getId()
                .equals(apartment.getId()))
            failure.setAssignedTo(apartment.getBuilding()
                    .getManager());
        failure.setApartment(apartment);
        failure.setDescription(request.description());

        if (request.photoToDelete() != null && !request.photoToDelete().isEmpty()) {
            var photosToDelete = failure.getPhotos()
                    .stream()
                    .filter(photo -> request.photoToDelete().contains(photo.getId()))
                    .toList();

            photoRepository.deleteAll(photosToDelete);
            photosToDelete.stream()
                    .map(Photo::getUrl)
                    .forEach(storageService::deleteFile);

            failure.getPhotos().removeAll(photosToDelete);
        }

        if (photos != null && !photos.isEmpty()) {
            var photosEntities = createPhotosList(photos, failure);
            failure.getPhotos().addAll(photosEntities);
        }

        return FailureMapper.toFailureShortResponse(failure);
    }

    @Override
    @Transactional
    public FailureShortResponse changeFailureStatus(Long id,
                                                    FailureChangeStatusRequest request) {
        var failure = failureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono awarii"));

        if (failure.getStatus().cantChangeTo(request.status()))
            throw new IllegalStateException("Nie można zmienić status");
        failure.setStatus(request.status());

        return FailureMapper.toFailureShortResponse(failure);
    }

    @Override
    public void deleteFailure(Long id) {
        var failure = failureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono awarii"));

        failure.getPhotos()
                .stream()
                .map(Photo::getUrl)
                .forEach(storageService::deleteFile);
        failureRepository.delete(failure);
    }

    private List<Photo> createPhotosList(List<MultipartFile> photos, Failure savedFailure) {
        List<Photo> photosEntities = new ArrayList<>();
        photos.forEach(file -> {
            var url = storageService.saveFile(file.getOriginalFilename(), file);
            var photo = Photo.builder()
                    .name(file.getOriginalFilename())
                    .url(url)
                    .failure(savedFailure)
                    .build();

            photosEntities.add(photo);
        });
        photoRepository.saveAll(photosEntities);
        return photosEntities;
    }
}
