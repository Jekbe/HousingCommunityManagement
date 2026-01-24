package pl.edu.uws.pp.domain.dto.login;

public record LoginRequest(
        String email,
        String password
) {
}
