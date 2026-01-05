package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.apartment.ApartmentRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentResponse;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.entity.Apartment;
import pl.edu.uws.pp.domain.entity.Building;

public class ApartmentMapper {
    private ApartmentMapper() {}

    public static Apartment fromApartmentRequest(ApartmentRequest request, Building building){
        return Apartment.builder()
                .building(building)
                .number(request.number())
                .build();
    }

    public static ApartmentShortResponse toApartmentShortResponse(Apartment apartment){
        return ApartmentShortResponse.builder()
                .id(apartment.getId())
                .number(apartment.getNumber())
                .build();
    }

    public static ApartmentResponse toApartmentResponse(Apartment apartment){
        return ApartmentResponse.builder()
                .apartmentId(apartment.getId())
                .building(BuildingMapper.toBuildingShortResponse(apartment.getBuilding()))
                .number(apartment.getNumber())
                .residentsInfo(apartment.getResidents()
                        .stream()
                        .map(UserMapper::toUserShortResponse)
                        .toList())
                .invoices(apartment.getInvoices()
                        .stream()
                        .map(InvoiceMapper::toInvoiceShortResponse)
                        .toList())
                .failures(apartment.getFailures()
                        .stream()
                        .map(FailureMapper::toFailureShortResponse)
                        .toList())
                .build();
    }
}
