package pl.edu.uws.pp.domain.dto.apartment;

import lombok.Builder;

@Builder
public record ApartmentRequest(
        Long buildingId,
        int number
) {
}
