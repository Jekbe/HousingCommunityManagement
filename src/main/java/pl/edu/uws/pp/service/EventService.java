package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.event.*;

public interface EventService {
    EventResponse createEvent(EventRequest request);
    EventResponse getEventInfo(Long id);
    EventResponse editEvent(Long id, EventEditRequest request);
    EventResponse changeEventStatus(Long  id, EventChangeStatusRequest request);
    void deleteEvent(Long id);
}
