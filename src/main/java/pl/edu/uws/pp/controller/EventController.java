package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.config.security.UserPrincipal;
import pl.edu.uws.pp.domain.dto.event.*;
import pl.edu.uws.pp.service.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PutMapping
    public EventShortResponse createEvent(@RequestBody EventRequest request){
        return eventService.createEvent(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public EventResponse eventInfo(@PathVariable Long id,
                                   @AuthenticationPrincipal UserPrincipal user){
        return eventService.getEventInfo(id, user);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PutMapping("/{id}")
    public EventShortResponse editEvent(@PathVariable Long id,
                                   @RequestBody EventEditRequest request){
        return eventService.editEvent(id, request);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @PatchMapping("/{id}/status")
    public EventShortResponse changeEventStatus(@PathVariable Long id,
                                           @RequestBody EventChangeStatusRequest request){
        return eventService.changeEventStatus(id, request);
    }

    @PreAuthorize("hasRole('HOUSING_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }
}
