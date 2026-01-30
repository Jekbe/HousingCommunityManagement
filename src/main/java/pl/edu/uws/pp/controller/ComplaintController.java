package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.complaint.*;
import pl.edu.uws.pp.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PreAuthorize("hasRole('RESIDENT')")
    @PostMapping
    public ComplaintShortResponse creteComplaint(@RequestBody ComplaintRequest request,
                                                 @AuthenticationPrincipal UserPrincipal user) {
        return complaintService.createComplaint(request, user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ComplaintResponse complaintInfo(@PathVariable Long id,
                                           @AuthenticationPrincipal UserPrincipal user){
        return complaintService.getComplaintInfo(id, user);
    }

    @PreAuthorize("hasRole('RESIDENT')")
    @PutMapping("/{id}")
    public ComplaintShortResponse editComplaint(@PathVariable Long id,
                                                @RequestBody ComplaintEditRequest request,
                                                @AuthenticationPrincipal UserPrincipal user){
        return complaintService.editComplaint(id, request, user);
    }

    @PreAuthorize("hasRole('BUILDING_MANAGER')")
    @PatchMapping("/{id}/status")
    public ComplaintShortResponse changeComplaintStatus(@PathVariable Long id,
                                                        @RequestBody ComplaintChangeStatusRequest request,
                                                        @AuthenticationPrincipal UserPrincipal user){
        return complaintService.changeComplaintStatus(id, request, user);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id){
        complaintService.deleteComplaint(id);
    }
}
