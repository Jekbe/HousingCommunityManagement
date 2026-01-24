package pl.edu.uws.pp.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.failure.*;

import java.util.List;

public interface FailureService {
    FailureShortResponse createFailure(FailureRequest request,
                                       List<MultipartFile> photos);
    FailureResponse getFailureInfo(Long failureId);
    FailureShortResponse editFailure(Long id,
                                FailureEditRequest request,
                                List<MultipartFile> photos);
    FailureShortResponse changeFailureStatus(Long id,
                                        FailureChangeStatusRequest request);
    void deleteFailure(Long id);
}
