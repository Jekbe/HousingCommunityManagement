package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.uws.pp.domain.dto.failure.FailureChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureEditRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureRequest;
import pl.edu.uws.pp.domain.dto.failure.FailureResponse;
import pl.edu.uws.pp.service.FailureService;

import java.util.List;

@Service
public class FailureServiceImpl implements FailureService {
    @Override
    public FailureResponse createFailure(FailureRequest request,
                                         List<MultipartFile> photos) {
        return null;
    }

    @Override
    public FailureResponse getFailureInfo(Long failureId) {
        return null;
    }

    @Override
    public FailureResponse editFailure(Long id,
                                       FailureEditRequest request,
                                       List<MultipartFile> photos) {
        return null;
    }

    @Override
    public FailureResponse changeFailureStatus(Long id,
                                               FailureChangeStatusRequest request) {
        return null;
    }

    @Override
    public void deleteFailure(Long id) {

    }
}
