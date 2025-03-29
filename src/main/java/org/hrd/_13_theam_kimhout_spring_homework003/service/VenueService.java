package org.hrd._13_theam_kimhout_spring_homework003.service;

import org.hrd._13_theam_kimhout_spring_homework003.model.Venue;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer offset, Integer limit);

    Venue getVenueById(Integer venueId);

    Venue createVenue(VenueRequest venueRequest);

    Venue updateVenueById(Integer venueId, VenueRequest venueRequest);

    void deleteVenueById(Integer venueId);

}
