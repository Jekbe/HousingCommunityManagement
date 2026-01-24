package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.entity.User;

public interface JwtService {
    String generateToken(User user);
    Long extractUserId(String token);
}
