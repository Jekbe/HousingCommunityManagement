package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.User.*;

import java.util.List;

public interface UserService {
    UserShortResponse createUser(UserRequest request);
    List<UserShortResponse> getUsersList();
    UserProfileResponse getUserProfile(Long id);
    UserShortResponse editUser(Long id, UserEditRequest request);
    UserShortResponse addApartmentForUser(Long userId, Long apartmentId);
    UserShortResponse deleteApartmentForUser(Long userId, Long apartmentId);
    void deleteUser(Long id);
}
