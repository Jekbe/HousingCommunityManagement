package pl.edu.uws.pp.domain.dto.User;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
        String name,
        String Surname,
        String email,
        String Password
) {
}
