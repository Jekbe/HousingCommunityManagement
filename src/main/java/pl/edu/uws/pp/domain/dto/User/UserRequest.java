package pl.edu.uws.pp.domain.dto.User;

import pl.edu.uws.pp.domain.enums.Role;

public record UserRequest(
        String name,
        String surname,
        String pesel,
        String email,
        Role role
) {
}
