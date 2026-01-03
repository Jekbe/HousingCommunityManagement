package pl.edu.uws.pp.domain.dto.User;

import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintShortResponse;
import pl.edu.uws.pp.domain.dto.failure.FailureShortResponse;
import pl.edu.uws.pp.domain.enums.Role;

import java.util.List;

public record UserProfileResponse(
        Long userId,
        Long profileId,
        String name,
        String surname,
        String pesel,
        String email,
        Role role,
        List<BuildingShortResponse> buildings,
        List<FailureShortResponse> failures,
        List<ComplaintShortResponse> complaints
) {
}
