package pl.edu.uws.pp.domain.dto.User;

public record UserShortResponse(
        Long UserId,
        String name,
        String surname,
        String email
) {
}
