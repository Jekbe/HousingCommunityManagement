package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.apartment.*;
import pl.edu.uws.pp.service.ApartmentService;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PostMapping
    public ApartmentShortResponse createApartment(@RequestBody ApartmentRequest request){
        return apartmentService.createApartment(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ApartmentResponse apartmentInfo(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal user){
        return apartmentService.getApartmentInfo(id, user);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PutMapping("/{id}")
    public ApartmentShortResponse editApartment(@PathVariable Long id,
                                           @RequestBody ApartmentEditRequest request){
        return apartmentService.editApartment(id, request);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id){
        apartmentService.deleteApartment(id);
    }
}
