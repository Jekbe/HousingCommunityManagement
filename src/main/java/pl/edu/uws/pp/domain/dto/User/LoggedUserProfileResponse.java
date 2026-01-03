package pl.edu.uws.pp.domain.dto.User;

import pl.edu.uws.pp.domain.enums.Role;

public record LoggedUserProfileResponse(
        Long id,
        String name,
        String surname,
        String pesel,
        String email,
        Role role
) {
}
