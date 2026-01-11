package pl.edu.uws.pp.domain.dto.User;

public record UserApartmentRequest(
        Long userId,
        Long apartmentId
) {
}
