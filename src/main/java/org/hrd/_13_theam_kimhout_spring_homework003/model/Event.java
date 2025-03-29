package org.hrd._13_theam_kimhout_spring_homework003.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    private Integer eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Venue venue;
    private List<Attendee> attendeeList;
}
