package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.building.BuildingEditRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingRequest;
import pl.edu.uws.pp.domain.dto.building.BuildingResponse;
import pl.edu.uws.pp.domain.dto.building.BuildingShortResponse;
import pl.edu.uws.pp.domain.mapper.BuildingMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.AddressRepository;
import pl.edu.uws.pp.repository.BuildingRepository;
import pl.edu.uws.pp.repository.ManagerRepository;
import pl.edu.uws.pp.service.BuildingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final AddressRepository addressRepository;
    private final ManagerRepository managerRepository;

    @Override
    public BuildingResponse createBuilding(BuildingRequest request) {
        var address = BuildingMapper.fromBuildingRequestCreateAddress(request);
        var savedAddress = addressRepository.save(address);
        var manager = managerRepository.findById(request.managerId())
                .orElseThrow(() -> new NotFoundException("nie znaleziono managera"));
        var building = BuildingMapper.fromAddressAndManagerCreateBuilding(savedAddress, manager);
        var savedBuilding = buildingRepository.save(building);

        return BuildingMapper.toBuildingResponse(savedBuilding);
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
    public BuildingResponse editBuilding(Long id,
                                         BuildingEditRequest request) {
        return null;
    }

    @Override
    public void deleteBuilding(Long id) {

    }
}
