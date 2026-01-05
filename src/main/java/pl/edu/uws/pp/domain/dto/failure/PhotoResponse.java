package pl.edu.uws.pp.domain.dto.failure;

import lombok.Builder;

@Builder
public record PhotoResponse(
        String name,
        String url
) {
}
