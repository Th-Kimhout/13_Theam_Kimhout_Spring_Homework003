package org.hrd._13_theam_kimhout_spring_homework003.repo;

import org.apache.ibatis.annotations.*;
import org.hrd._13_theam_kimhout_spring_homework003.model.Attendee;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.AttendeeRequest;

import java.util.List;


@Mapper
public interface AttendeeRepo {

    //-- Select with Pagination

    @Select("SELECT * FROM attendees offset #{offset} limit #{limit}")
    @Results(id = "AttendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "attendeeEmail", column = "attendee_email")
    })
    List<Attendee> getAllAttendees(Integer offset, Integer limit);


    //-- find by id
    @Select("SELECT * FROM attendees where attendee_id = #{attendeeId}")
    @ResultMap("AttendeeMapper")
    Attendee findAttendeeById(Integer attendeeId);


    //-- delete by id
    @Delete("DELETE FROM attendees where attendee_id = #{attendeeId}")
    void deleteAttendee(Integer attendeeId);


    //-- insert attendee
    @Select("INSERT INTO attendees(attendee_name, attendee_email) values (#{atd.attendeeName}, #{atd.attendeeEmail}) RETURNING *")
    @ResultMap("AttendeeMapper")
    Attendee createAttendee(@Param("atd") AttendeeRequest attendeeRequest);

    //-- update attendee
    @Select("UPDATE attendees SET attendee_name = #{atd.attendeeName}, attendee_email = #{atd.attendeeEmail} WHERE attendee_id = #{attendeeId} RETURNING *")
    @ResultMap("AttendeeMapper")
    Attendee updateAttendee(Integer attendeeId,@Param("atd") AttendeeRequest attendeeRequest);


}
