package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.building.*;
import pl.edu.uws.pp.domain.enums.Role;
import pl.edu.uws.pp.domain.mapper.ApartmentMapper;
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
    public BuildingShortResponse createBuilding(BuildingRequest request) {
        var manager = managerRepository.findById(request.managerId())
                .orElseThrow(() -> new NotFoundException("nie znaleziono managera"));

        var address = BuildingMapper.fromBuildingRequestCreateAddress(request);
        var savedAddress = addressRepository.save(address);

        var building = BuildingMapper.fromAddressAndManagerCreateBuilding(savedAddress, manager);
        var savedBuilding = buildingRepository.save(building);

        return BuildingMapper.toBuildingShortResponse(savedBuilding);
    }

    @Override
    public List<BuildingShortResponse> getBuildingList(UserPrincipal principal) {
        var buildingList = buildingRepository.findAll();

        var user = principal.user();
        if (user.isRoleEqualed(Role.BUILDING_MANAGER)){
            buildingList = buildingRepository.findAllByManager(user.getManagerProfile());
        }

        return buildingList.stream()
                .map(BuildingMapper::toBuildingShortResponse)
                .toList();
    }

    @Override
    public BuildingResponse getBuildingInfo(Long id,
                                            UserPrincipal principal) {
        var building = buildingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono budynku"));
        var apartmentsList = building.getApartments();



        var user =  principal.user();
        if (user.isRoleEqualed(Role.BUILDING_MANAGER)
            && ! user.getManagerProfile()
                .getManagedBuildings()
                .contains(building)) {
            throw new AccessDeniedException("Brak dostępu do tego budynku");
        }
        if (user.isRoleEqualed(Role.RESIDENT)){
            if (user.getResidentProfile().hasNotApartmentInBuilding(building)) {
                throw new AccessDeniedException("Brak dostępu do tego budynku");
            }

            apartmentsList = apartmentsList.stream()
                    .filter(apartment ->
                            user.getResidentProfile()
                                    .isOwningApartment(apartment))
                    .toList();
        }

        var apartmentShortResponsesList = apartmentsList.stream()
                .map(ApartmentMapper::toApartmentShortResponse)
                .toList();

        return BuildingMapper.toBuildingResponse(building, apartmentShortResponsesList);
    }

    @Override
    @Transactional
    public BuildingShortResponse editBuilding(Long id,
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

        return BuildingMapper.toBuildingShortResponse(building);
    }

    @Override
    public void deleteBuilding(Long id) {
        var building = buildingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("nie znaleziono budynku"));
        buildingRepository.delete(building);
    }
}
