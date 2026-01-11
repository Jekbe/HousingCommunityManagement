package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.apartment.ApartmentEditRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentResponse;

public interface ApartmentService {
    ApartmentResponse createApartment(ApartmentRequest request);
    ApartmentResponse getApartmentInfo(Long id);
    ApartmentResponse editApartment(ApartmentEditRequest request);
    void deleteApartment(Long id);
}