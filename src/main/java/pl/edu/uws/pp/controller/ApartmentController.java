package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.apartment.*;
import pl.edu.uws.pp.service.ApartmentService;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @PostMapping
    public ApartmentShortResponse createApartment(@RequestBody ApartmentRequest request){
        return apartmentService.createApartment(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ApartmentResponse apartmentInfo(@PathVariable Long id){
        return apartmentService.getApartmentInfo(id);
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
