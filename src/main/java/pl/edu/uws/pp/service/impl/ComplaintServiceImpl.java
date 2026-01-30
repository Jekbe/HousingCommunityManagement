package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.complaint.*;
import pl.edu.uws.pp.domain.enums.Role;
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
    public ComplaintShortResponse createComplaint(ComplaintRequest request,
                                                  UserPrincipal principal) {
        var manager = managerRepository.findById(request.managerId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono managera"));
        var user = principal.user();
        if (! user.getResidentProfile().hasApartmentManagedByManager(manager)) {
            throw new AccessDeniedException("Nie możesz złożyć skargi do tego managera");
        }

        var complaint = ComplaintMapper.fromComplaintRequest(request, manager, user.getResidentProfile());
        var savedComplaint = complaintRepository.save(complaint);

        return ComplaintMapper.toComplaintShortResponse(savedComplaint);
    }

    @Override
    public ComplaintResponse getComplaintInfo(Long id,
                                              UserPrincipal principal) {
        var  complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono skargi"));

        var user = principal.user();
        if (user.isRoleEqualed(Role.BUILDING_MANAGER)
            && ! complaint.getAssignedTo().equals(user.getManagerProfile())) {
            throw new AccessDeniedException("Nie masz dostępu do tej skargi");
        }
        if (user.isRoleEqualed(Role.RESIDENT)
            &&  ! complaint.getReporting().equals(user.getResidentProfile())) {
            throw new AccessDeniedException("Nie masz dostępu do tej skargi");
        }

        return ComplaintMapper.toComplaintResponse(complaint);
    }

    @Override
    @Transactional
    public ComplaintShortResponse editComplaint(Long id,
                                                ComplaintEditRequest request,
                                                UserPrincipal principal) {
        var complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono skargi"));

        var user =  principal.user();
        if (! complaint.getReporting().equals(user.getResidentProfile())) {
            throw new AccessDeniedException("Nie możesz edytować nie swojej skargi");
        }

        complaint.setDescription(request.Description());

        return ComplaintMapper.toComplaintShortResponse(complaint);
    }

    @Override
    @Transactional
    public ComplaintShortResponse changeComplaintStatus(Long id,
                                                        ComplaintChangeStatusRequest request,
                                                        UserPrincipal principal) {
        var complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono skargi"));

        var user = principal.user();
        if (! complaint.getAssignedTo().equals(user.getManagerProfile())){
            throw new AccessDeniedException("Nie możesz zmienić statusu tej skargi");
        }

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
