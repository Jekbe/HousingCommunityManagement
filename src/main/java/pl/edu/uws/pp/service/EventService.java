package pl.edu.uws.pp.service;

import pl.edu.uws.pp.domain.dto.event.EventEditRequest;
import pl.edu.uws.pp.domain.dto.event.EventRequest;
import pl.edu.uws.pp.domain.dto.event.EventResponse;
import pl.edu.uws.pp.domain.dto.event.EventChangeStatusRequest;

public interface EventService {
    EventResponse createEvent(EventRequest request);
    EventResponse getEventInfo(Long id);
    EventResponse editEvent(Long id, EventEditRequest request);
    EventResponse changeEventStatus(Long  id, EventChangeStatusRequest request);
    void deleteStatus(Long id);
}
