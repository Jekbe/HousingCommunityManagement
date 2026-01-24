package pl.edu.uws.pp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.domain.dto.event.*;
import pl.edu.uws.pp.domain.mapper.EventMapper;
import pl.edu.uws.pp.exception.NotFoundException;
import pl.edu.uws.pp.repository.BuildingRepository;
import pl.edu.uws.pp.repository.EventRepository;
import pl.edu.uws.pp.service.EventService;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final BuildingRepository buildingRepository;

    @Override
    public EventShortResponse createEvent(EventRequest request) {
        var building = buildingRepository.findById(request.BuildingId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono budynku"));
        var event = EventMapper.fromEventRequest(request, building);
        var savedEvent = eventRepository.save(event);

        return EventMapper.toEventShortResponse(savedEvent);
    }

    @Override
    public EventResponse getEventInfo(Long id) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wydarzenia"));


        return EventMapper.toEventResponse(event);
    }

    @Override
    @Transactional
    public EventShortResponse editEvent(Long id,
                                        EventEditRequest request) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wydarzenia"));
        var building = buildingRepository.findById(request.buildingId())
                .orElseThrow(() -> new NotFoundException("Nie znaleziono budynku"));

        event.setBuilding(building);
        event.setTitle(request.tittle());
        event.setDescription(request.description());
        event.setEventTime(request.eventTime());

        return EventMapper.toEventShortResponse(event);
    }

    @Override
    @Transactional
    public EventShortResponse changeEventStatus(Long id,
                                                EventChangeStatusRequest request) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wydarzenia"));
        if (!event.getStatus().canChangeTo(request.status()))
            throw new IllegalStateException("Nie prawidłowa zmiana statusu");

        event.setStatus(request.status());

        return EventMapper.toEventShortResponse(event);
    }

    @Override
    public void deleteEvent(Long id) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wydarzenia"));
        eventRepository.delete(event);
    }
}
