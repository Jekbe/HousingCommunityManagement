package pl.edu.uws.pp.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.failure.FailureChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureEditRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureResponse;

import java.util.List;

public interface FailureService {
    FailureResponse createFailure(FailureRequest request,
                                  List<MultipartFile> photos);
    FailureResponse getFailureInfo(Long failureId);
    FailureResponse editFailure(Long id,
                                FailureEditRequest request,
                                List<MultipartFile> photos);
    FailureResponse changeFailureStatus(Long id,
                                        FailureChangeStatusRequest request);
    void deleteFailure(Long id);
}
