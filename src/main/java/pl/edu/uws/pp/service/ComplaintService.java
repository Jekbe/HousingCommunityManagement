package pl.edu.uws.pp.service;

import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.complaint.*;

public interface ComplaintService {
    ComplaintShortResponse createComplaint(ComplaintRequest request, UserPrincipal principal);
    ComplaintResponse getComplaintInfo(Long id, UserPrincipal principal);
    ComplaintShortResponse editComplaint(Long id, ComplaintEditRequest request, UserPrincipal principal);
    ComplaintShortResponse changeComplaintStatus(Long id, ComplaintChangeStatusRequest request, UserPrincipal principal);
    void deleteComplaint(Long id);
}
