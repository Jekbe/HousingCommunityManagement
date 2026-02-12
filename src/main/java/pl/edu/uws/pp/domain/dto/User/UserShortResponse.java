package pl.edu.uws.pp.domain.dto.User;

import lombok.Builder;
import pl.edu.uws.pp.domain.enums.Role;

@Builder
public record UserShortResponse(
        Long userId,
        String name,
        String surname,
        String email,
        Role role
) {
}
