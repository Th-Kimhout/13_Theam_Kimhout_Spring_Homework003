package org.hrd._13_theam_kimhout_spring_homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hrd._13_theam_kimhout_spring_homework003.model.Attendee;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.ApiResponse;
import org.hrd._13_theam_kimhout_spring_homework003.model.dto.AttendeeRequest;
import org.hrd._13_theam_kimhout_spring_homework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attendees")

public class AttendeeController {

    private final AttendeeService attendeeService;

    @Operation(summary = "Get all attendees")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1") @Positive(message = "Offset must be greater than 0!") Integer offset, @RequestParam(defaultValue = "10") @Positive(message = "Limit must be greater than 0!") Integer limit) {
        List<Attendee> attendeeList = attendeeService.getAllAttendees(offset, limit);
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .message("Retrieve all attendees successfully!")
                .status(HttpStatus.OK)
                .payload(attendeeList)
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get attendee by Id")
    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable(name = "attendee-id") @Positive(message = "Attendee's id must be greater than 0!") Integer attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Retrieve attendee successfully!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create attendee")
    @PostMapping()
    public ResponseEntity<ApiResponse<Attendee>> addAttendee(@RequestBody @Valid AttendeeRequest attendeeRequest) {
        Attendee newAttendee = attendeeService.createAttendee(attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Create attendee successfully!")
                .status(HttpStatus.CREATED)
                .payload(newAttendee)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update attendee by id")
    @PutMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(@PathVariable(name = "attendee-id") @Positive(message = "Attendee's id must be greater than 0") Integer attendeeId, @RequestBody @Valid AttendeeRequest attendeeRequest) {
        Attendee updatedAttendee = attendeeService.updateAttendeeById(attendeeId, attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Update attendee successfully!")
                .status(HttpStatus.OK)
                .payload(updatedAttendee)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete attendee by id")
    @DeleteMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendee(@PathVariable(name = "attendee-id") @Positive(message = "Attendee's id must be greater than 0") Integer attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Attendee deleted successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.ok(response);
    }

}
