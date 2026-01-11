package pl.edu.uws.pp.domain.dto.User;

public record UserEditRequest(
        Long userId,
        String name,
        String surname,
        String pesel,
        String email,
        String password
) {
}
