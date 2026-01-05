package pl.edu.uws.pp.domain.dto.failure;

public record FailureRequest(
        Long apartmentId,
        String description
) {
}
