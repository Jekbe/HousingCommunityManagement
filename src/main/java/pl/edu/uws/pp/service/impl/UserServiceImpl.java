package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.User.*;
import pl.edu.uws.pp.domain.entity.*;
import pl.edu.uws.pp.domain.enums.Role;
import pl.edu.uws.pp.domain.mapper.*;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.*;
import pl.edu.uws.pp.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ResidentRepository residentRepository;
    private final ManagerRepository managerRepository;
    private final ApartmentRepository apartmentRepository;
    private final FailureRepository failureRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final ComplaintRepository complaintRepository;
    private final BuildingRepository buildingRepository;
    private final EventRepository eventRepository;

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
    public UserProfileResponse getUserProfile(Long id, UserPrincipal principal) {
        var viewer = principal.user();
        var target = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));

        List<Apartment> apartments = new ArrayList<>();
        List<Failure> failures = new ArrayList<>();
        List<Invoice> invoices = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();
        List<Complaint> complaints = new ArrayList<>();
        List<Building> buildings = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        if (viewer.equals(target)) {
            if (viewer.isRoleEqualed(Role.HOUSING_MANAGER)){
                apartments = apartmentRepository.findAll();
                failures = failureRepository.findAll();
                invoices = invoiceRepository.findAll();
                payments = paymentRepository.findAll();
                complaints = complaintRepository.findAll();
                buildings = buildingRepository.findAll();
                events = eventRepository.findAll();
            } else if (viewer.isRoleEqualed(Role.BUILDING_MANAGER)){
                var manager = viewer.getManagerProfile();
                apartments = apartmentRepository.findAllByBuildingIn(manager.getManagedBuildings());
                failures = failureRepository.findAllByAssignedTo(manager);
                invoices = invoiceRepository.findAllByApartment_Building_Manager(manager);
                payments = paymentRepository.findAllByInvoice_Apartment_Building_Manager(manager);
                complaints = complaintRepository.findAllByAssignedTo(manager);
                buildings = buildingRepository.findAllByManager(manager);
                events = eventRepository.findAllByBuilding_Manager(manager);
            } else if (viewer.isRoleEqualed(Role.RESIDENT)){
                var resident = viewer.getResidentProfile();
                apartments = apartmentRepository.findAllByResidentsContaining(resident);
                failures = failureRepository.findAllByApartment_ResidentsContaining(resident);
                invoices = invoiceRepository.findAllByApartment_ResidentsContaining(resident);
                payments = paymentRepository.findAllByInvoice_Apartment_ResidentsContains(resident);
                complaints = complaintRepository.findAllByReporting(resident);
                events = eventRepository.findAllByBuilding_Apartments_ResidentsContains(resident);
            }
        } else if (viewer.isRoleEqualed(Role.RESIDENT)){
            if (target.isRoleEqualed(Role.RESIDENT)){
                apartments = apartmentRepository.findAllByResidentsContainingAndResidentsContaining(viewer.getResidentProfile(), target.getResidentProfile());
            } else if (target.isRoleEqualed(Role.BUILDING_MANAGER)){
                apartments = apartmentRepository.findAllByResidentsContainingAndBuilding_Manager(viewer.getResidentProfile(), target.getManagerProfile());
                failures = failureRepository.findAllByReportingAndAssignedTo(viewer.getResidentProfile(), target.getManagerProfile());
                complaints = complaintRepository.findAllByReportingAndAssignedTo(viewer.getResidentProfile(), target.getManagerProfile());
            }
        } else if (viewer.isRoleEqualed(Role.BUILDING_MANAGER)){
            if (target.isRoleEqualed(Role.RESIDENT)){
                apartments = apartmentRepository.findAllByResidentsContainingAndBuilding_Manager(target.getResidentProfile(), viewer.getManagerProfile());
                failures = failureRepository.findAllByReportingAndAssignedTo(target.getResidentProfile(), viewer.getManagerProfile());
                payments = paymentRepository.findAllByInvoice_Apartment_ResidentsContainsAndInvoice_Apartment_Building_Manager(target.getResidentProfile(), viewer.getManagerProfile());
                complaints = complaintRepository.findAllByReportingAndAssignedTo(target.getResidentProfile(), viewer.getManagerProfile());
            }
        } else if (viewer.isRoleEqualed(Role.HOUSING_MANAGER)){
            if (target.isRoleEqualed(Role.RESIDENT)){
                var resident = target.getResidentProfile();
                apartments = apartmentRepository.findAllByResidentsContaining(resident);
                failures = failureRepository.findAllByApartment_ResidentsContaining(resident);
                invoices =  invoiceRepository.findAllByApartment_ResidentsContaining(resident);
                payments = paymentRepository.findAllByInvoice_Apartment_ResidentsContains(resident);
                complaints = complaintRepository.findAllByReporting(resident);
            } else if (target.isRoleEqualed(Role.BUILDING_MANAGER)){
                var manager = target.getManagerProfile();
                apartments = apartmentRepository.findAllByBuildingIn(manager.getManagedBuildings());
                failures = failureRepository.findAllByAssignedTo(manager);
                complaints = complaintRepository.findAllByAssignedTo(manager);
                buildings = buildingRepository.findAllByManager(manager);
            }
        }

        return UserMapper.toUserProfileResponse(viewer,
                target,
                apartments.stream().map(ApartmentMapper::toApartmentShortResponse).toList(),
                failures.stream().map(FailureMapper::toFailureShortResponse).toList(),
                invoices.stream().map(InvoiceMapper::toInvoiceShortResponse).toList(),
                payments.stream().map(PaymentMapper::toPaymentShortResponse).toList(),
                complaints.stream().map(ComplaintMapper::toComplaintShortResponse).toList(),
                buildings.stream().map(BuildingMapper::toBuildingShortResponse).toList(),
                events.stream().map(EventMapper::toEventShortResponse).toList());
    }

    @Override
    @Transactional
    public UserShortResponse editUser(Long id,
                                      UserEditRequest request,
                                      UserPrincipal principal) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));

        var loggedUser = principal.user();
        if (loggedUser.isRoleEqualed(Role.RESIDENT)
            && ! loggedUser.equals(user)) {
            throw new AccessDeniedException("Nie możesz zmienić danych innego użytkownika");
        }
        if (loggedUser.isRoleEqualed(Role.HOUSING_MANAGER)){
            user.setName(request.name());
            user.setSurname(request.surname());
            user.setPesel(request.pesel());
        }
        user.setEmail(request.email());
        user.setPassword(request.password());

        return UserMapper.toUserShortResponse(user);
    }

    @Override
    @Transactional
    public UserShortResponse addApartmentForUser(Long userId,
                                                 Long apartmentId,
                                                 UserPrincipal principal) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));
        var loggedUser = principal.user();
        if (loggedUser.isRoleEqualed(Role.BUILDING_MANAGER)
            && loggedUser.getManagerProfile().isNotManagingApartment(apartment)) {
            throw new AccessDeniedException("Nie możesz dodać mieszkańca do budynku którym nie zarządzasz");
        }

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
                                                    Long apartmentId,
                                                    UserPrincipal principal) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono użytkownika"));
        var apartment = apartmentRepository.findById(apartmentId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono mieszkania"));
        var loggedUser = principal.user();
        if (loggedUser.isRoleEqualed(Role.BUILDING_MANAGER)
            && loggedUser.getManagerProfile().isNotManagingApartment(apartment)) {
            throw new AccessDeniedException("Nie możesz usunąć użytkownika z mieszkania którym nie zarządzasz");
        }

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
