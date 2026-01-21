package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        var manager = managerRepository.findById(request.managerId())
                .orElseThrow(() -> new NotFoundException("nie znaleziono managera"));
        var address = BuildingMapper.fromBuildingRequestCreateAddress(request);
        var savedAddress = addressRepository.save(address);
        var building = BuildingMapper.fromAddressAndManagerCreateBuilding(savedAddress, manager);
        var savedBuilding = buildingRepository.save(building);

        return BuildingMapper.toBuildingResponse(savedBuilding);
    }

    @Override
    public List<BuildingShortResponse> getBuildingList() {
        var buildingList = buildingRepository.findAll();

        return buildingList.stream()
                .map(BuildingMapper::toBuildingShortResponse)
                .toList();
    }

    @Override
    public BuildingResponse getBuildingInfo(Long id) {
        var building = buildingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono budynku"));

        return BuildingMapper.toBuildingResponse(building);
    }

    @Override
    @Transactional
    public BuildingResponse editBuilding(Long id,
                                         BuildingEditRequest request) {
        var building = buildingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono budynku"));
        var manager = managerRepository.findById(request.managerId())
                        .orElseThrow(() -> new NotFoundException("Nie znaleziono managera"));

        building.getAddress()
                .setCountry(request.country());
        building.getAddress()
                .setPostalCode(request.postalCode());
        building.getAddress()
                .setCity(request.city());
        building.getAddress()
                .setStreet(request.street());
        building.getAddress()
                .setBuildingNumber(request.buildingNumber());
        building.setManager(manager);

        return BuildingMapper.toBuildingResponse(building);
    }

    @Override
    public void deleteBuilding(Long id) {
        var building = buildingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono budynku"));
        buildingRepository.delete(building);
    }
}
