package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.complaint.*;

public interface ComplaintService {
    ComplaintShortResponse createComplaint(ComplaintRequest request);
    ComplaintResponse getComplaintInfo(Long id);
    ComplaintShortResponse editComplaint(Long id, ComplaintEditRequest request);
    ComplaintShortResponse changeComplaintStatus(Long id, ComplaintChangeStatusRequest request);
    void deleteComplaint(Long id);
}
