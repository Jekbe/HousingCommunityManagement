package pl.edu.uws.pp.domain.dto.apartment;

import lombok.Builder;

@Builder
public record ApartmentShortResponse(
        Long id,
        String number
) {
}
