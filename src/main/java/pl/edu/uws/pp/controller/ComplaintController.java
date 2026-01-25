package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.complaint.*;
import pl.edu.uws.pp.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PreAuthorize("hasRole('RESIDENT')")
    @PostMapping
    public ComplaintShortResponse creteComplaint(@RequestBody ComplaintRequest request){
        return complaintService.createComplaint(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ComplaintResponse complaintInfo(@PathVariable Long id){
        return complaintService.getComplaintInfo(id);
    }

    @PreAuthorize("hasRole('RESIDENT')")
    @PutMapping("/{id}")
    public ComplaintShortResponse editComplaint(@PathVariable Long id,
                                           @RequestBody ComplaintEditRequest request){
        return complaintService.editComplaint(id, request);
    }

    @PreAuthorize("hasRole('BUILDING_MANAGER')")
    @PatchMapping("/{id}/status")
    public ComplaintShortResponse changeComplaintStatus(@PathVariable Long id,
                                                   @RequestBody ComplaintChangeStatusRequest request){
        return complaintService.changeComplaintStatus(id, request);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id){
        complaintService.deleteComplaint(id);
    }
}
