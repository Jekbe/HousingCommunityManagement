package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.building.*;
import pl.edu.uws.pp.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PostMapping
    public BuildingShortResponse createBuilding(@RequestBody BuildingRequest request){
        return buildingService.createBuilding(request);
    }

    @PreAuthorize("hasAnyRole('HOUSING_MANAGER', 'BUILDING_MANAGER')")
    @GetMapping
    public List<BuildingShortResponse> buildingsList(){
        return buildingService.getBuildingList();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public BuildingResponse buildingInfo(@PathVariable Long id){
        return buildingService.getBuildingInfo(id);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PutMapping("/{id}")
    public BuildingShortResponse editBuilding(@PathVariable Long id,
                                         @RequestBody BuildingEditRequest request){
        return buildingService.editBuilding(id, request);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable Long id){
        buildingService.deleteBuilding(id);
    }
}
