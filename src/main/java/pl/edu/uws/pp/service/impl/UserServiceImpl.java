package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.User.*;
import pl.edu.uws.pp.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse createUser(UserRequest request) {
        return null;
    }

    @Override
    public List<UserShortResponse> getUsersList() {
        return List.of();
    }

    @Override
    public UserProfileResponse getUserProfile(Long id) {
        return null;
    }

    @Override
    public UserProfileResponse editUser(Long id,
                                        UserEditRequest request) {
        return null;
    }

    @Override
    public UserProfileResponse addApartmentForUser(Long userId,
                                                   Long apartmentId) {
        return null;
    }

    @Override
    public UserProfileResponse deleteApartmentForUser(Long userId,
                                                      Long apartmentId) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
