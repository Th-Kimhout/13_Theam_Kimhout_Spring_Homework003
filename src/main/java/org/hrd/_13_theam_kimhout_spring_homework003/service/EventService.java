package org.hrd._13_theam_kimhout_spring_homework003.service;

import org.hrd._13_theam_kimhout_spring_homework003.model.Event;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer offset, Integer limit);

    Event getEventById(Integer eventId);

    Event createEvent(EventRequest eventRequest);

    Event updateEventById(Integer eventId, EventRequest eventRequest);

    void deleteEventById(Integer eventId);
}
