package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.failure.FailureChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureEditRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureResponse;
import pl.edu.uws.pp.service.FailureService;

import java.util.List;

@RestController
@RequestMapping("/failure")
@RequiredArgsConstructor
public class FailureController {
    private final FailureService failureService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FailureResponse createFailure(@RequestPart("data") FailureRequest request,
                                         @RequestPart(value = "photos", required = false) List<MultipartFile> photos){
        return failureService.createFailure(request, photos);
    }

    @GetMapping("/{id}")
    public FailureResponse failureInfo(@PathVariable Long id){
        return failureService.getFailureInfo(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FailureResponse editFailure(@PathVariable Long id,
                                       @RequestPart("data") FailureEditRequest request,
                                       @RequestPart(value = "photo", required = false) List<MultipartFile> photos){
        return failureService.editFailure(id, request, photos);
    }

    @PatchMapping("/{id}")
    public FailureResponse changeFailureStatus(@PathVariable Long id,
                                               @RequestBody FailureChangeStatusRequest request){
        return failureService.changeFailureStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteFailure(@PathVariable Long id){
        failureService.deleteFailure(id);
    }
}
