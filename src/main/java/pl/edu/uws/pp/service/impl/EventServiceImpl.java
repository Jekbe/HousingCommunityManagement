package pl.edu.uws.pp.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.uws.pp.domain.dto.event.EventChangeStatusRequest;
import pl.edu.uws.pp.domain.dto.event.EventEditRequest;
import pl.edu.uws.pp.domain.dto.event.EventRequest;
import pl.edu.uws.pp.domain.dto.event.EventResponse;
import pl.edu.uws.pp.service.EventService;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public EventResponse createEvent(EventRequest request) {
        return null;
    }

    @Override
    public EventResponse getEventInfo(Long id) {
        return null;
    }

    @Override
    public EventResponse editEvent(Long id, EventEditRequest request) {
        return null;
    }

    @Override
    public EventResponse changeEventStatus(Long id, EventChangeStatusRequest request) {
        return null;
    }

    @Override
    public void deleteStatus(Long id) {

    }
}
