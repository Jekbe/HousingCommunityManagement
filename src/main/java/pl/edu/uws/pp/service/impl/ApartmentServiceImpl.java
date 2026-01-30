package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.apartment.*;
import pl.edu.uws.pp.domain.enums.Role;
import pl.edu.uws.pp.domain.mapper.ApartmentMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.ApartmentRepository;
import pl.edu.uws.pp.repository.BuildingRepository;
import pl.edu.uws.pp.service.ApartmentService;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final BuildingRepository buildingRepository;

    @Override
    public ApartmentShortResponse createApartment(ApartmentRequest request) {
        var building = buildingRepository.findById(request.buildingId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono budynku"));

        var apartment = ApartmentMapper.fromApartmentRequest(request, building);
        var savedApartment = apartmentRepository.save(apartment);

        return ApartmentMapper.toApartmentShortResponse(savedApartment);
    }

    @Override
    public ApartmentResponse getApartmentInfo(Long id,
                                              UserPrincipal principal) {
        var apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        var user = principal.user();
        if (user.isRoleEqualed(Role.BUILDING_MANAGER)
                && user.getManagerProfile().isNotManagingApartment(apartment)) {
            throw new AccessDeniedException("Nie masz dostępu do tego mieszkania");
        }
        if (user.isRoleEqualed(Role.RESIDENT)
                && ! principal.user().getResidentProfile().isOwningApartment(apartment)) {
            throw new AccessDeniedException("Nie masz dostępu do tego mieszkania");
        }

        return ApartmentMapper.toApartmentResponse(apartment);
    }

    @Override
    @Transactional
    public ApartmentShortResponse editApartment(Long id,
                                                ApartmentEditRequest request) {
        var apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        apartment.setNumber(request.number());

        return ApartmentMapper.toApartmentShortResponse(apartment);
    }

    @Override
    public void deleteApartment(Long id) {
        var apartment = apartmentRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        apartmentRepository.delete(apartment);
    }
}
