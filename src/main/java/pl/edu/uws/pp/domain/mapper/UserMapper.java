package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.User.*;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;
import pl.edu.uws.pp.domain.entity.User;

public class UserMapper {
    private UserMapper() {}

    public static User fromUserRequest(UserRequest request){
        return User.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .pesel(request.pesel())
                .role(request.role())
                .build();
    }

    public static void fromUserUpdateRequest(User user, UserUpdateRequest request) {
        user.setName(request.name());
        user.setSurname(request.Surname());
        user.setEmail(request.email());
        user.setPassword(request.Password());
    }

    public static UserShortResponse toUserShortResponse(User user){
        return UserShortResponse.builder()
                .UserId(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static UserShortResponse toUserShortResponse(Resident resident){
        return toUserShortResponse(resident.getUser());
    }

    public static UserShortResponse toUserShortResponse(Manager manager){
        return toUserShortResponse(manager.getUser());
    }

    private static ManagerProfileResponse setManagerInfo(Manager manager) {
        return ManagerProfileResponse.builder()
                .userId(manager.getUser().getId())
                .profileId(manager.getId())
                .name(manager.getUser().getName())
                .surname(manager.getUser().getSurname())
                .pesel(manager.getUser().getPesel())
                .email(manager.getUser().getEmail())
                .role(manager.getUser().getRole())
                .managedBuildings(manager.getManagedBuildings()
                        .stream()
                        .map(BuildingMapper::toBuildingShortResponse)
                        .toList())
                .assignedFailures(manager.getAssignedFailures()
                        .stream()
                        .map(FailureMapper::toFailureShortResponse)
                        .toList())
                .assignedComplaints(manager.getManagedComplaints()
                        .stream()
                        .map(ComplaintMapper::toComplaintShortResponse)
                        .toList())
                .build();
    }

    private static ResidentProfileResponse setResidentInfo(Resident resident) {
        return ResidentProfileResponse.builder()
                .userId(resident.getUser().getId())
                .profileId(resident.getId())
                .name(resident.getUser().getName())
                .surname(resident.getUser().getSurname())
                .pesel(resident.getUser().getPesel())
                .email(resident.getUser().getEmail())
                .role(resident.getUser().getRole())
                .apartments(resident.getApartments()
                        .stream()
                        .map(ApartmentMapper::toApartmentShortResponse)
                        .toList())
                .failures(resident.getReportedFailures()
                        .stream()
                        .map(FailureMapper::toFailureShortResponse)
                        .toList())
                .invoices(resident.getInvoices()
                        .stream()
                        .map(InvoiceMapper::toInvoiceShortResponse)
                        .toList())
                .payments(resident.getPayments()
                        .stream()
                        .map(PaymentMapper::toPaymentShortResponse)
                        .toList())
                .complaints(resident.getComplaints()
                        .stream()
                        .map(ComplaintMapper::toComplaintShortResponse)
                        .toList())
                .build();
    }
}
