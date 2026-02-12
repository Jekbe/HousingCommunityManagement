package pl.edu.uws.pp.domain.dto.login;

import pl.edu.uws.pp.domain.enums.Role;

public record LoginResponse(
        String token,
        Long id,
        Role role
) {
}
