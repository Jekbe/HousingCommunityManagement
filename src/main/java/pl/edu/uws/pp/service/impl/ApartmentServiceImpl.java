package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentEditRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentResponse;
import pl.edu.uws.pp.domain.entity.Apartment;
import pl.edu.uws.pp.domain.entity.Building;
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
    public ApartmentResponse createApartment(ApartmentRequest request) {
        var building = buildingRepository.findById(request.buildingId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono budynku"));
        var apartment = ApartmentMapper.fromApartmentRequest(request, building);
        var saved = apartmentRepository.save(apartment);

        return ApartmentMapper.toApartmentResponse(saved);
    }

    @Override
    public ApartmentResponse getApartmentInfo(Long id) {
        var apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        return ApartmentMapper.toApartmentResponse(apartment);
    }

    @Override
    @Transactional
    public ApartmentResponse editApartment(Long id,
                                           ApartmentEditRequest request) {
        var apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));
        apartment.setNumber(request.number());

        return ApartmentMapper.toApartmentResponse(apartment);
    }

    @Override
    public void deleteApartment(Long id) {
        var apartment = apartmentRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Nie znaleziiono mieszkania"));
        apartmentRepository.delete(apartment);
    }
}
