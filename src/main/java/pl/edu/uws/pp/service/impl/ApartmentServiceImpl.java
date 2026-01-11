package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentEditRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentResponse;
import pl.edu.uws.pp.service.ApartmentService;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Override
    public ApartmentResponse createApartment(ApartmentRequest request) {
        return null;
    }

    @Override
    public ApartmentResponse getApartmentInfo(Long id) {
        return null;
    }

    @Override
    public ApartmentResponse editApartment(Long id, ApartmentEditRequest request) {
        return null;
    }

    @Override
    public void deleteApartment(Long id) {

    }
}
