package pl.edu.uws.pp.domain.dto.User;

public record UserUpdateRequest(
        String name,
        String Surname,
        String email,
        String Password
) {
}
