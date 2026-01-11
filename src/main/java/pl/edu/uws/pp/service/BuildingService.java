package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.building.BuildingEditRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingResponse;

public interface BuildingService {
    BuildingResponse createBuilding(BuildingRequest request);
    BuildingResponse getBuildingInfo(Long id);
    BuildingResponse editBuilding(BuildingEditRequest request);
    void deleteBuilding(Long id);
}
