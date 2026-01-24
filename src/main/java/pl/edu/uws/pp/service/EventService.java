package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.event.*;

public interface EventService {
    EventShortResponse createEvent(EventRequest request);
    EventResponse getEventInfo(Long id);
    EventShortResponse editEvent(Long id, EventEditRequest request);
    EventShortResponse changeEventStatus(Long  id, EventChangeStatusRequest request);
    void deleteEvent(Long id);
}
