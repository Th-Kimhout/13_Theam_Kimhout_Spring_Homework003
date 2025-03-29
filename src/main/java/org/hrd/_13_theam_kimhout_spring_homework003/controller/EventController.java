package org.hrd._13_theam_kimhout_spring_homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework003.model.Event;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.ApiResponse;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.EventRequest;
import org.hrd._13_theam_kimhout_spring_homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Get all events")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") @Positive(message = "Offset must be greater than 0!") Integer offset, @RequestParam(defaultValue = "10") @Positive(message = "Limit must be greater than 0!") Integer limit) {

        List<Event> eventList = eventService.getAllEvents(offset, limit);
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("Retrieve all events successfully!")
                .status(HttpStatus.OK)
                .payload(eventList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get event by id")
    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") @Positive(message = "Event's id must be greater than 0!") Integer eventId) {
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Retrieve event successfully!")
                .status(HttpStatus.OK)
                .payload(event)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Create event")
    @PostMapping()
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        Event event = eventService.createEvent(eventRequest);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Create event successfully!")
                .status(HttpStatus.CREATED)
                .payload(event)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "Update event by id")
    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@PathVariable(name = "event-id") @Positive(message = "Event's id must be greater than 0!") Integer eventId, @Valid @RequestBody EventRequest eventRequest) {
        Event event = eventService.updateEventById(eventId, eventRequest);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Update event successfully!")
                .status(HttpStatus.OK)
                .payload(event)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "Delete event by id")
    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable(name = "event-id") @Positive(message = "Event's id must be greater than 0!") Integer eventId) {
        eventService.deleteEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Update event successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
