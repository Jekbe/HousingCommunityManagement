package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.building.*;
import pl.edu.uws.pp.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/buildings")
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
    public List<BuildingShortResponse> buildingsList(@AuthenticationPrincipal UserPrincipal user){
        return buildingService.getBuildingList(user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public BuildingResponse buildingInfo(@PathVariable Long id,
                                         @AuthenticationPrincipal UserPrincipal user){
        return buildingService.getBuildingInfo(id, user);
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
