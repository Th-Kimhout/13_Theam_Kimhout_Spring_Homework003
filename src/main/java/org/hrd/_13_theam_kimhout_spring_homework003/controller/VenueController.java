package org.hrd._13_theam_kimhout_spring_homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework003.model.Venue;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.ApiResponse;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.VenueRequest;
import org.hrd._13_theam_kimhout_spring_homework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    @Operation(summary = "Get all venue")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") @Positive(message = "Offset must be greater than 0!") Integer offset, @RequestParam(defaultValue = "10") @Positive(message = "Limit must be greater than 0!") Integer limit) {
        List<Venue> venueList = venueService.getAllVenues(offset, limit);
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .message("Retrieve all venues successfully!")
                .status(HttpStatus.OK)
                .payload(venueList)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get venue by id")
    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable(name = "venue-id") @Positive(message = "Venue's id must be greater than 0!") Integer venueId) {

        Venue venue = venueService.getVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Retrieve venue successfully!")
                .status(HttpStatus.OK)
                .payload(venue)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create venue")
    @PostMapping()
    public ResponseEntity<ApiResponse<Venue>> addVenue(@RequestBody @Valid VenueRequest venueRequest) {
        Venue newVenue = venueService.createVenue(venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Create venue successfully!")
                .status(HttpStatus.CREATED)
                .payload(newVenue)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update venue by id")
    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(@PathVariable(name = "venue-id") @Positive(message = "Venue's id must be greater than 0!") Integer venueId, @RequestBody @Valid VenueRequest venueRequest) {
        Venue updateVenue = venueService.updateVenueById(venueId, venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Update venue successfully!")
                .status(HttpStatus.OK)
                .payload(updateVenue)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete venue")
    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenue(@PathVariable(name = "venue-id") @Positive(message = "Venue's id must be greater than 0!") Integer venueId) {
        venueService.deleteVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Venue deleted successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    }
