package pl.edu.uws.pp.domain.mapper;

import pl.edu.uws.pp.domain.dto.complaint.ComplaintRequest;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintResponse;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintShortResponse;
import pl.edu.uws.pp.domain.entity.Complaint;
import pl.edu.uws.pp.domain.entity.Manager;
import pl.edu.uws.pp.domain.entity.Resident;
import pl.edu.uws.pp.domain.enums.FailureStatus;

public class ComplaintMapper {
    private ComplaintMapper() {}

    public static Complaint fromComplaintRequest(ComplaintRequest request,
                                                 Manager manager,
                                                 Resident resident) {
        return Complaint.builder()
                .reporting(resident)
                .assignedTo(manager)
                .description(request.description())
                .status(FailureStatus.NEW)
                .build();
    }

    public static ComplaintShortResponse toComplaintShortResponse(Complaint complaint) {
        return ComplaintShortResponse.builder()
                .complaintId(complaint.getId())
                .description(complaint.getDescription())
                .status(complaint.getStatus())
                .creationTime(complaint.getCreatedAt())
                .build();
    }

    public static ComplaintResponse toComplaintResponse(Complaint complaint) {
        return ComplaintResponse.builder()
                .complaintId(complaint.getId())
                .reporting(UserMapper.toUserShortResponse(complaint.getReporting()))
                .manager(UserMapper.toUserShortResponse(complaint.getAssignedTo()))
                .description(complaint.getDescription())
                .status(complaint.getStatus())
                .creationTime(complaint.getCreatedAt())
                .build();
    }
}
