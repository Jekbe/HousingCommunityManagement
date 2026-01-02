package pl.edu.uws.pp.domain.dto.User;

public record ChangeUserInfoRequest(
        String name,
        String Surname,
        String email,
        String Password
) {
}
