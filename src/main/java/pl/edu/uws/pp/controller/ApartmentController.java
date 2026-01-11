package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentEditRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentRequest;
import pl.edu.uws.pp.domain.dto.apartment.ApartmentResponse;
import pl.edu.uws.pp.service.ApartmentService;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping
    public ApartmentResponse createApartment(@RequestBody ApartmentRequest request){
        return apartmentService.createApartment(request);
    }

    @GetMapping("/{id}")
    public ApartmentResponse apartmentInfo(@PathVariable Long id){
        return apartmentService.getApartmentInfo(id);
    }

    @PutMapping("/{id}")
    public ApartmentResponse editApartment(@PathVariable Long id, @RequestBody ApartmentEditRequest request){
        return apartmentService.editApartment(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id){
        apartmentService.deleteApartment(id);
    }
}
