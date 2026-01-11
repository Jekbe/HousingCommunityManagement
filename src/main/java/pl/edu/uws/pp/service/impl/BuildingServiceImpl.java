package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.building.BuildingEditRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.service.BuildingService;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Override
    public BuildingResponse createBuilding(BuildingRequest request) {
        return null;
    }

    @Override
    public List<BuildingShortResponse> getBuildingList() {
        return List.of();
    }

    @Override
    public BuildingResponse getBuildingInfo(Long id) {
        return null;
    }

    @Override
    public BuildingResponse editBuilding(Long id, BuildingEditRequest request) {
        return null;
    }

    @Override
    public void deleteBuilding(Long id) {

    }
}
