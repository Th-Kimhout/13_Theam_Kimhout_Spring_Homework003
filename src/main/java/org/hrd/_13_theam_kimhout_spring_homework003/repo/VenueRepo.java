package org.hrd._13_theam_kimhout_spring_homework003.repo;

import org.apache.ibatis.annotations.*;
import org.hrd._13_theam_kimhout_spring_homework003.model.Venue;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepo {

    //-- select all venue with pagination
    @Select("SELECT * FROM venues offset #{offset} limit #{limit}")
    @Results(id = "VenueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    List<Venue> getAllVenues(Integer offset, Integer limit);

    //-- find venue by id
    @Select("SELECT * FROM venues WHERE venue_id = #{venueId}")
    @ResultMap("VenueMapper")
    Venue getVenueById(Integer venueId);

    //-- delete venue by id
    @Delete("DELETE FROM venues WHERE venue_id = #{venueId}")
    void deleteVenueById(Integer venueId);

    //-- create venue
    @Select("INSERT INTO venues(venue_name, location) VALUES (#{venue.venueName}, #{venue.location}) RETURNING *")
    @ResultMap("VenueMapper")
    Venue createVenue(@Param("venue") VenueRequest venueRequest);

    //-- update venue
    @Select("UPDATE venues SET venue_name = #{venue.venueName}, location =  #{venue.location} WHERE venue_id = #{venueId} RETURNING *")
    @ResultMap("VenueMapper")
    Venue updateVenue(Integer venueId,@Param("venue") VenueRequest venueRequest);
}
