package pl.edu.uws.pp.domain.dto.failure;

import lombok.Builder;

@Builder
public record PhotoResponse(
        Long photoId,
        String name,
        String url
) {
}
