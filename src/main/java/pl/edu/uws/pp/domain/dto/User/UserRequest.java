package pl.edu.uws.pp.domain.dto.User;

import lombok.Builder;
import pl.edu.uws.pp.domain.enums.Role;

@Builder
public record UserRequest(
        String name,
        String surname,
        String pesel,
        String email,
        Role role
) {
}
