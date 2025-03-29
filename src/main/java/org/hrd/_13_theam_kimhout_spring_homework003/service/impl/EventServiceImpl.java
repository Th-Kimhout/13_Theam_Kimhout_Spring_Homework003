package org.hrd._13_theam_kimhout_spring_homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework003.exception.NotFoundException;
import org.hrd._13_theam_kimhout_spring_homework003.model.Event;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.EventRequest;
import org.hrd._13_theam_kimhout_spring_homework003.repo.AttendeeRepo;
import org.hrd._13_theam_kimhout_spring_homework003.repo.EventRepo;
import org.hrd._13_theam_kimhout_spring_homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepo eventRepo;
    private final AttendeeRepo attendeeRepo;

    @Override
    public List<Event> getAllEvents(Integer offset, Integer limit) {
        Integer page = limit * (offset - 1);
        return eventRepo.getAllEvents(page, limit);
    }

    @Override
    public Event getEventById(Integer eventId) {
        Event event = eventRepo.findEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Cannot find event with id " + eventId + " !");
        }
        return event;
    }

    @Override
    public Event createEvent(EventRequest eventRequest) {
        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            if (attendeeRepo.findAttendeeById(attendeeId) == null) {
                throw new NotFoundException("Cannot find attendee with id " + attendeeId + "to add to event !");
            }
        }
        Integer createdEventId = eventRepo.createEvent(eventRequest);
        if (createdEventId == null) {
            throw new NotFoundException("Cannot create event!");
        }
        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            eventRepo.insertEventAttendee(createdEventId, attendeeId);
        }
        return eventRepo.findEventById(createdEventId);
    }

    @Override
    public Event updateEventById(Integer eventId, EventRequest eventRequest) {
        Event event = eventRepo.findEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Cannot find event with id " + eventId + " !");
        }

        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            if (attendeeRepo.findAttendeeById(attendeeId) == null) {
                throw new NotFoundException("Cannot find attendee with id " + attendeeId + " to update for the event !");
            }
        }
        eventRepo.updateEventEvent(eventId, eventRequest);
        eventRepo.deleteEventAttendees(eventId);

        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            eventRepo.insertEventAttendee(eventId, attendeeId);
        }

        return eventRepo.findEventById(eventId);
    }

    @Override
    public void deleteEventById(Integer eventId) {
        if (eventRepo.findEventById(eventId) == null) {
            throw new NotFoundException("Cannot find event with id " + eventId + " !");
        }
        eventRepo.deleteEventById(eventId);
    }
}
