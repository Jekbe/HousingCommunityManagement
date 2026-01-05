package pl.edu.uws.pp.domain.dto.User;

import lombok.Builder;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintShortResponse;
import pl.edu.uws.pp.domain.dto.failure.FailureShortResponse;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.List;

@Builder
public record ManagerProfileResponse(
        Long userId,
        Long profileId,
        String name,
        String surname,
        String pesel,
        String email,
        Role role,
        List<BuildingShortResponse> managedBuildings,
        List<FailureShortResponse> assignedFailures,
        List<ComplaintShortResponse> assignedComplaints
) {
}
