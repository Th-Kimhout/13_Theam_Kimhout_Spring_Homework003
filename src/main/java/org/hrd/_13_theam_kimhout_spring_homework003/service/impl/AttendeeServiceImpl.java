package org.hrd._13_theam_kimhout_spring_homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework003.exception.NotFoundException;
import org.hrd._13_theam_kimhout_spring_homework003.model.Attendee;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.AttendeeRequest;
import org.hrd._13_theam_kimhout_spring_homework003.repo.AttendeeRepo;
import org.hrd._13_theam_kimhout_spring_homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepo attendeeRepo;

    @Override
    public List<Attendee> getAllAttendees(Integer offset, Integer limit) {
        Integer page = limit * (offset - 1);

        return attendeeRepo.getAllAttendees(page, limit);
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {

        Attendee attendee = attendeeRepo.findAttendeeById(attendeeId);
        if (attendee == null) {
            throw new NotFoundException("Cannot find attendee with id " + attendeeId + " !");
        }
        return attendee;
    }

    @Override
    public Attendee createAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepo.createAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest attendeeRequest) {

        if (attendeeRepo.findAttendeeById(attendeeId) == null) {
            throw new NotFoundException("Cannot find attendee with id " + attendeeId + " !");
        }
        return attendeeRepo.updateAttendee(attendeeId, attendeeRequest);
    }

    @Override
    public void deleteAttendeeById(Integer attendeeId) {
        if (attendeeRepo.findAttendeeById(attendeeId) == null) {
            throw new NotFoundException("Cannot find attendee with id " + attendeeId + " !");
        }
        attendeeRepo.deleteAttendee(attendeeId);
    }
}
