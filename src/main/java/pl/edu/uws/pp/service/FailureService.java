package pl.edu.uws.pp.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.failure.*;

import java.util.List;

public interface FailureService {
    FailureShortResponse createFailure(FailureRequest request,
                                       List<MultipartFile> photos, UserPrincipal principal);
    FailureResponse getFailureInfo(Long failureId, UserPrincipal principal);
    FailureShortResponse editFailure(Long id,
                                     FailureEditRequest request,
                                     List<MultipartFile> photos, UserPrincipal principal);
    FailureShortResponse changeFailureStatus(Long id,
                                             FailureChangeStatusRequest request, UserPrincipal principal);
    void deleteFailure(Long id);
}
