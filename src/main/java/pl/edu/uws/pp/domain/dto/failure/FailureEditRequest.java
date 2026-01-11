package pl.edu.uws.pp.domain.dto.failure;

import java.util.List;

public record FailureEditRequest(
        Long failureId,
        Long apartmentId,
        String description,
        List<Long> photoToDelete
) {
}
