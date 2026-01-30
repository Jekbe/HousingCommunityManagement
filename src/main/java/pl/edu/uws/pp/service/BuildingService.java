package pl.edu.uws.pp.service;

import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.building.BuildingEditRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;

import java.util.List;

public interface BuildingService {
    BuildingShortResponse createBuilding(BuildingRequest request);
    List<BuildingShortResponse> getBuildingList(UserPrincipal principal);
    BuildingResponse getBuildingInfo(Long id, UserPrincipal principal);
    BuildingShortResponse editBuilding(Long id, BuildingEditRequest request);
    void deleteBuilding(Long id);
}
