package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.domain.dto.complaint.*;
import pl.edu.uws.pp.domain.mapper.ComplaintMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.ComplaintRepository;
import pl.edu.uws.pp.repository.ManagerRepository;
import pl.edu.uws.pp.service.ComplaintService;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository complaintRepository;
    private final ManagerRepository managerRepository;

    @Override
    public ComplaintShortResponse createComplaint(ComplaintRequest request) {
        var manager = managerRepository.findById(request.managerId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono managera"));
        var complaint = ComplaintMapper.fromComplaintRequest(request, manager);
        var savedComplaint = complaintRepository.save(complaint);

        return ComplaintMapper.toComplaintShortResponse(savedComplaint);
    }

    @Override
    public ComplaintResponse getComplaintInfo(Long id) {
        var  complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono skargi"));

        return ComplaintMapper.toComplaintResponse(complaint);
    }

    @Override
    @Transactional
    public ComplaintShortResponse editComplaint(Long id,
                                                ComplaintEditRequest request) {
        var complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono skargi"));
        complaint.setDescription(request.Description());

        return ComplaintMapper.toComplaintShortResponse(complaint);
    }

    @Override
    @Transactional
    public ComplaintShortResponse changeComplaintStatus(Long id,
                                                        ComplaintChangeStatusRequest request) {
        var complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono skargi"));
        if (complaint.getStatus().cantChangeTo(request.status()))
            throw new IllegalStateException("Nie prawidłowa zmiana statusu");

        complaint.setStatus(request.status());

        return ComplaintMapper.toComplaintShortResponse(complaint);
    }

    @Override
    public void deleteComplaint(Long id) {
        var complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono skargi"));
        complaintRepository.delete(complaint);
    }
}
