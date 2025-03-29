package org.hrd._13_theam_kimhout_spring_homework003.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hrd._13_theam_kimhout_spring_homework003.model.Attendee;
import org.hrd._13_theam_kimhout_spring_homework003.model.Venue;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class EventRequest {
    @NotBlank(message = "Event's name cannot be empty!")
    @Length(max = 50, message = "Event's name cannot exceed 50 characters!")
    private String eventName;
    @NotNull(message = "Event's date time cannot be null!")
    private LocalDateTime eventDate;
    @NotNull(message = "Event's venue id cannot be null!")
    private Integer venueId;
    @NotEmpty(message = "Attendee's id list cannot be empty!")
    private List<Integer> attendeeIds;
}
