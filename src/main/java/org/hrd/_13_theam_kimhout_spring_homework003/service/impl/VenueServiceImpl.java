package org.hrd._13_theam_kimhout_spring_homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework003.exception.NotFoundException;
import org.hrd._13_theam_kimhout_spring_homework003.model.Venue;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.VenueRequest;
import org.hrd._13_theam_kimhout_spring_homework003.repo.VenueRepo;
import org.hrd._13_theam_kimhout_spring_homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepo venueRepo;

    @Override
    public List<Venue> getAllVenues(Integer offset, Integer limit) {
        Integer page = limit * (offset - 1);
        return venueRepo.getAllVenues(page, limit);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        Venue venue = venueRepo.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Cannot find venue with id " + venueId + " !");
        }
        return venue;
    }

    @Override
    public Venue createVenue(VenueRequest venueRequest) {
        return venueRepo.createVenue(venueRequest);
    }

    @Override
    public Venue updateVenueById(Integer venueId, VenueRequest venueRequest) {
        if (venueRepo.getVenueById(venueId) == null) {
            throw new NotFoundException("Cannot find venue with id " + venueId + " !");
        }
        return venueRepo.updateVenue(venueId, venueRequest);
    }

    @Override
    public void deleteVenueById(Integer venueId) {
        if (venueRepo.getVenueById(venueId) == null) {
            throw new NotFoundException("Cannot find venue with id " + venueId + " !");
        }
        venueRepo.deleteVenueById(venueId);
    }
}
