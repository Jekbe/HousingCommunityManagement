package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.failure.*;
import pl.edu.uws.pp.service.FailureService;

import java.util.List;

@RestController
@RequestMapping("/failure")
@RequiredArgsConstructor
public class FailureController {
    private final FailureService failureService;

    @PreAuthorize("hasRole('RESIDENT')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FailureShortResponse createFailure(@RequestPart("data") FailureRequest request,
                                              @RequestPart(value = "photos", required = false) List<MultipartFile> photos){
        return failureService.createFailure(request, photos);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public FailureResponse failureInfo(@PathVariable Long id){
        return failureService.getFailureInfo(id);
    }

    @PreAuthorize("hasRole('RESIDENT')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FailureShortResponse editFailure(@PathVariable Long id,
                                       @RequestPart("data") FailureEditRequest request,
                                       @RequestPart(value = "photo", required = false) List<MultipartFile> photos){
        return failureService.editFailure(id, request, photos);
    }

    @PreAuthorize("hasRole('BUILDING_MANAGER')")
    @PatchMapping("/{id}")
    public FailureShortResponse changeFailureStatus(@PathVariable Long id,
                                               @RequestBody FailureChangeStatusRequest request){
        return failureService.changeFailureStatus(id, request);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteFailure(@PathVariable Long id){
        failureService.deleteFailure(id);
    }
}
