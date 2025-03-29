package org.hrd._13_theam_kimhout_spring_homework003.service;

import org.hrd._13_theam_kimhout_spring_homework003.model.Attendee;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.AttendeeRequest;

import java.util.List;


public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer offset, Integer limit);

    Attendee getAttendeeById(Integer attendeeId);

    Attendee createAttendee(AttendeeRequest attendeeRequest);

    Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest attendeeRequest);

    void deleteAttendeeById(Integer attendeeId);
}
