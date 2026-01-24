package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.domain.dto.User.*;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;
import pl.edu.uws.pp.domain.enums.Role;
import pl.edu.uws.pp.domain.mapper.UserMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.ApartmentRepository;
import pl.edu.uws.pp.repository.ManagerRepository;
import pl.edu.uws.pp.repository.ResidentRepository;
import pl.edu.uws.pp.repository.UserRepository;
import pl.edu.uws.pp.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ResidentRepository residentRepository;
    private final ManagerRepository managerRepository;
    private final ApartmentRepository apartmentRepository;

    @Override
    @Transactional
    public UserShortResponse createUser(UserRequest request) {
        var user = UserMapper.fromUserRequest(request);
        switch (user.getRole()) {
            case RESIDENT -> {
                var resident = Resident.builder()
                        .user(user)
                        .build();
                residentRepository.save(resident);
            }
            case BUILDING_MANAGER -> {
                var manager = Manager.builder()
                        .user(user)
                        .build();
                managerRepository.save(manager);
            }
            default -> throw new IllegalStateException("Problem z rolą");
        }

        var savedUser = userRepository.save(user);


        return UserMapper.toUserShortResponse(savedUser);
    }

    @Override
    public List<UserShortResponse> getUsersList() {
        var users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::toUserShortResponse)
                .toList();
    }

    @Override
    public UserProfileResponse getUserProfile(Long id) {


        return null;
    }

    @Override
    @Transactional
    public UserShortResponse editUser(Long id,
                                        UserEditRequest request) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));

        user.setName(request.name());
        user.setSurname(request.surname());
        user.setEmail(request.email());
        user.setPesel(request.pesel());
        user.setPassword(request.password());

        return UserMapper.toUserShortResponse(user);
    }

    @Override
    @Transactional
    public UserShortResponse addApartmentForUser(Long userId,
                                                   Long apartmentId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        if (user.getRole() != Role.RESIDENT)
            throw new IllegalStateException("Użytkownik nie jest mieszkańcem");

        user.getResidentProfile()
                .getApartments()
                .add(apartment);

        return UserMapper.toUserShortResponse(user);
    }

    @Override
    @Transactional
    public UserShortResponse deleteApartmentForUser(Long userId,
                                                      Long apartmentId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));

        if (user.getRole() != Role.RESIDENT)
            throw new IllegalStateException("Użytkownik nie jest mieszkańcem");

        if (!user.getResidentProfile()
                .getApartments()
                .contains(apartment))
            throw new IllegalStateException("Użytkownik nie posiada tego mieszkania");

        user.getResidentProfile()
                .getApartments()
                .remove(apartment);

        return UserMapper.toUserShortResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono user"));
        userRepository.delete(user);
    }
}
