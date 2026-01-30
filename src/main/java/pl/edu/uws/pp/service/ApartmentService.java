package pl.edu.uws.pp.service;

import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentEditRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentResponse;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;

public interface ApartmentService {
    ApartmentShortResponse createApartment(ApartmentRequest request);
    ApartmentResponse getApartmentInfo(Long id, UserPrincipal principal);
    ApartmentShortResponse editApartment(Long id, ApartmentEditRequest request);
    void deleteApartment(Long id);
}