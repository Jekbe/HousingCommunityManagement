package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.User.*;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentShortResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintShortResponse;
import pl.edu.uws.pp.domain.dto.event.EventShortResponse;
import pl.edu.uws.pp.domain.dto.failure.FailureShortResponse;
import pl.edu.uws.pp.domain.dto.invoice.InvoiceShortResponse;
import pl.edu.uws.pp.domain.dto.payment.PaymentShortResponse;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;
import pl.edu.uws.pp.domain.entity.User;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.List;

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

    public static UserProfileResponse toUserProfileResponse(User viewer,
                                                            User target,
                                                            List<ApartmentShortResponse> apartments,
                                                            List<FailureShortResponse> failures,
                                                            List<InvoiceShortResponse> invoices,
                                                            List<PaymentShortResponse> payments,
                                                            List<ComplaintShortResponse> complaints,
                                                            List<BuildingShortResponse> buildings,
                                                            List<EventShortResponse> events){
        return UserProfileResponse.builder()
                .userId(target.getId())
                .profileId(target.getRole() == Role.RESIDENT ? target.getResidentProfile().getId()
                        : target.getRole() == Role.BUILDING_MANAGER ? target.getManagerProfile().getId()
                        : null)
                .name(target.getName())
                .surname(target.getSurname())
                .pesel(viewer.canSeePesel(target) ? target.getPesel()
                        : "***********")
                .email(target.getEmail())
                .role(target.getRole())
                .apartments(apartments)
                .failures(failures)
                .invoices(invoices)
                .payments(payments)
                .complaints(complaints)
                .managedBuildings(buildings)
                .events(events)
                .build();
    }
}
