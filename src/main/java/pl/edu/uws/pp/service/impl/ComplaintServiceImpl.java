package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintEditRequest;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintRequest;
import pl.edu.uws.pp.domain.dto.complaint.ComplaintResponse;
import pl.edu.uws.pp.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Override
    public ComplaintResponse createComplaint(ComplaintRequest request) {
        return null;
    }

    @Override
    public ComplaintResponse getComplaintInfo(Long id) {
        return null;
    }

    @Override
    public ComplaintResponse editComplaint(Long id,
                                           ComplaintEditRequest request) {
        return null;
    }

    @Override
    public ComplaintResponse changeComplaintStatus(Long id,
                                                   ComplaintChangeStatusRequest request) {
        return null;
    }

    @Override
    public void deleteComplaint(Long id) {

    }
}
