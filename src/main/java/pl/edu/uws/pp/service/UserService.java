package pl.edu.uws.pp.service;

import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.User.*;

import java.util.List;

public interface UserService {
    UserShortResponse createUser(UserRequest request);
    List<UserShortResponse> getUsersList(UserPrincipal principal);
    UserProfileResponse getUserProfile(Long id, UserPrincipal principal);
    UserShortResponse editUser(Long id, UserEditRequest request, UserPrincipal principal);
    UserShortResponse addApartmentForUser(Long userId, Long apartmentId, UserPrincipal principal);
    UserShortResponse deleteApartmentForUser(Long userId, Long apartmentId, UserPrincipal principal);
    void deleteUser(Long id);
    List<UserShortResponse> getManagersList();
}
