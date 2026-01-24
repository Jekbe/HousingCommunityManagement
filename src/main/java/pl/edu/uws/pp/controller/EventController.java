package pl.edu.uws.pp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.uws.pp.domain.dto.event.*;
import pl.edu.uws.pp.service.EventService;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PutMapping
    public EventShortResponse createEvent(@RequestBody EventRequest request){
        return eventService.createEvent(request);
    }

    @GetMapping("/{id}")
    public EventResponse eventInfo(@PathVariable Long id){
        return eventService.getEventInfo(id);
    }

    @PutMapping("/{id}")
    public EventShortResponse editEvent(@PathVariable Long id,
                                   @RequestBody EventEditRequest request){
        return eventService.editEvent(id, request);
    }

    @PatchMapping("/{id}/status")
    public EventShortResponse changeEventStatus(@PathVariable Long id,
                                           @RequestBody EventChangeStatusRequest request){
        return eventService.changeEventStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }
}
