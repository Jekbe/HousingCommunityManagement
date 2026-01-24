package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.building.*;
import pl.edu.uws.pp.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping
    public BuildingShortResponse createBuilding(@RequestBody BuildingRequest request){
        return buildingService.createBuilding(request);
    }

    @GetMapping
    public List<BuildingShortResponse> buildingsList(){
        return buildingService.getBuildingList();
    }

    @GetMapping("/{id}")
    public BuildingResponse buildingInfo(@PathVariable Long id){
        return buildingService.getBuildingInfo(id);
    }

    @PutMapping("/{id}")
    public BuildingShortResponse editBuilding(@PathVariable Long id,
                                         @RequestBody BuildingEditRequest request){
        return buildingService.editBuilding(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable Long id){
        buildingService.deleteBuilding(id);
    }
}
