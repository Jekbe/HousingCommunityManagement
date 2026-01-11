package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.User.*;

import java.util.List;

public interface UserService {
    UserProfileResponse createUser(UserRequest request);
    List<UserShortResponse> getUsersList();
    UserProfileResponse getUserProfile(Long id);
    UserProfileResponse editUser(UserEditRequest request);
    UserProfileResponse addApartmentForUser(UserApartmentRequest request);
    UserProfileResponse deleteApartmentForUser(UserApartmentRequest request);
    void deleteUser(Long id);
}
