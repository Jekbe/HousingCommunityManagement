package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.complaint.ComplaintChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintEditRequest;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintRequest;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintResponse;

public interface ComplaintService {
    ComplaintResponse createComplaint(ComplaintRequest request);
    ComplaintResponse getComplaintInfo(Long id);
    ComplaintResponse editComplaint(Long id, ComplaintEditRequest request);
    ComplaintResponse changeComplaintStatus(Long id, ComplaintChangeStatusRequest request);
    void deleteComplaint(Long id);
}
