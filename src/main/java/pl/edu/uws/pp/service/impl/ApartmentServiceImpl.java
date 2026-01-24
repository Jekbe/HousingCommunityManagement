package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.domain.dto.apartment.*;
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
        var saved = apartmentRepository.save(apartment);

        return ApartmentMapper.toApartmentShortResponse(saved);
    }

    @Override
    public ApartmentResponse getApartmentInfo(Long id) {
        var apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

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
                        .orElseThrow(() -> new NotFoundException("Nie znaleziiono mieszkania"));
        apartmentRepository.delete(apartment);
    }
}
