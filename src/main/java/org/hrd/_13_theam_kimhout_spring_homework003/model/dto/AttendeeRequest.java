package org.hrd._13_theam_kimhout_spring_homework003.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
@AllArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Attendee's name cannot be empty!")
    @Length(max = 50, message = "Attendee's name cannot exceed 50 characters!")
    private String attendeeName;
    @Email(message = "Attendee's email must be in a correct format!")
    @NotBlank(message = "Attendee's email cannot be empty!")
    @Length(max = 50, message = "Attendee's email cannot exceed 50 characters!")
    private String attendeeEmail;
}
