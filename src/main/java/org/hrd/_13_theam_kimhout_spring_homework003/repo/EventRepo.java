package org.hrd._13_theam_kimhout_spring_homework003.repo;

import org.apache.ibatis.annotations.*;
import org.hrd._13_theam_kimhout_spring_homework003.model.Attendee;
import org.hrd._13_theam_kimhout_spring_homework003.model.Event;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.EventRequest;

import java.util.List;

@Mapper
public interface EventRepo {

    //-- select all event with pagination
    @Select("SELECT * FROM events offset #{offset} limit #{limit}")
    @Results(id = "EventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_Name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.hrd._13_theam_kimhout_spring_homework003.repo.VenueRepo.getVenueById")),
            @Result(property = "attendeeList", column = "event_id",
                    many = @Many(select = "findAllAttendeesByEventId"))
    })
    List<Event> getAllEvents(Integer offset, Integer limit);


    //-- find event by id
    @Select("SELECT * FROM events where event_id = #{eventId}")
    @ResultMap("EventMapper")
    Event findEventById(Integer eventId);


    //-- delete event by id
    @Delete("DELETE FROM events WHERE event_id = #{eventId}")
    void deleteEventById(Integer eventId);


    //-- create event
    @Select("INSERT INTO events(event_name, event_date, venue_id) VALUES (#{event.eventName}, #{event.eventDate}, #{event.venueId}) RETURNING event_id")
    Integer createEvent(@Param("event") EventRequest eventRequest);


    //-- insert attendees to the attendee_event
    @Insert("INSERT INTO event_attendee(event_id, attendee_id) VALUES (#{eventId}, #{attendeeId})")
    void insertEventAttendee(Integer eventId, Integer attendeeId);


    //-- update event
    @Update("UPDATE events SET event_name = #{event.eventName}, event_date = #{event.eventDate}, venue_id = #{event.venueId} WHERE event_id = #{eventId} ")
    void updateEventEvent(Integer eventId,@Param("event") EventRequest eventRequest);


    //-- delete existing event_attendee
    @Delete("DELETE FROM event_attendee where event_id = #{eventId}")
    void deleteEventAttendees(Integer eventId);


    @Select("""
                    SELECT * FROM attendees atd
                    INNER JOIN public.event_attendee ea
                    on atd.attendee_id = ea.attendee_id
                    WHERE event_id = #{eventId}
            """)
    @Result(property = "attendeeId", column = "attendee_id")
    @Result(property = "attendeeName", column = "attendee_name")
    @Result(property = "attendeeEmail", column = "attendee_email")
    List<Attendee> findAllAttendeesByEventId(Integer eventId);
}
