package pl.edu.uws.pp.domain.dto.failure;

import java.util.List;

public record FailureEditRequest(
        Long apartmentId,
        String description,
        List<Long> photoToDelete
) {
}
