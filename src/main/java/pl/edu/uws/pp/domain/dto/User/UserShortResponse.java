package pl.edu.uws.pp.domain.dto.User;

import lombok.Builder;
import pl.edu.uws.pp.domain.enums.Role;

@Builder
public record UserShortResponse(
        Long UserId,
        String name,
        String surname,
        String email,
        Role role
) {
}
