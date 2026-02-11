package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.failure.FailureRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureResponse;
import pl.edu.uws.pp.domain.dto.failure.FailureShortResponse;
import pl.edu.uws.pp.domain.dto.failure.PhotoResponse;
import pl.edu.uws.pp.domain.entity.Apartment;
import pl.edu.uws.pp.domain.entity.Failure;
import pl.edu.uws.pp.domain.entity.Photo;
import pl.edu.uws.pp.domain.entity.Resident;
import pl.edu.uws.pp.domain.enums.FailureStatus;

public class FailureMapper {
    private FailureMapper() {}

    public static Failure fromFailureRequest(FailureRequest request, Apartment apartment, Resident resident) {
        return Failure.builder()
                .apartment(apartment)
                .description(request.description())
                .reporting(resident)
                .assignedTo(apartment.getBuilding().getManager())
                .status(FailureStatus.NEW)
                .build();
    }

    public static FailureShortResponse toFailureShortResponse(Failure failure) {
        return FailureShortResponse.builder()
                .failureId(failure.getId())
                .description(failure.getDescription())
                .status(failure.getStatus())
                .creationTime(failure.getCreatedAt())
                .build();
    }

    public static FailureResponse toFailureResponse(Failure failure) {
        return FailureResponse.builder()
                .failureId(failure.getId())
                .reporting(UserMapper.toUserShortResponse(failure.getReporting()))
                .manager(UserMapper.toUserShortResponse(failure.getAssignedTo()))
                .apartment(ApartmentMapper.toApartmentShortResponse(failure.getApartment()))
                .description(failure.getDescription())
                .photos(failure.getPhotos()
                        .stream()
                        .map(FailureMapper::toPhotoResponse)
                        .toList())
                .status(failure.getStatus())
                .creationTime(failure.getCreatedAt())
                .build();
    }

    private static PhotoResponse toPhotoResponse(Photo photo) {
        return PhotoResponse.builder()
                .photoId(photo.getId())
                .name(photo.getName())
                .build();
    }
}
