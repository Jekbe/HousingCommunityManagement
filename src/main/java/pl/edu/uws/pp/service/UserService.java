package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.User.*;

import java.util.List;

public interface UserService {
    UserProfileResponse createUser(UserRequest request);
    List<UserShortResponse> getUsersList();
    UserProfileResponse getUserProfile(Long id);
    UserProfileResponse editUser(Long id, UserEditRequest request);
    UserProfileResponse addApartmentForUser(Long userId, Long apartmentId);
    UserProfileResponse deleteApartmentForUser(Long userId, Long apartmentId);
    void deleteUser(Long id);
}
