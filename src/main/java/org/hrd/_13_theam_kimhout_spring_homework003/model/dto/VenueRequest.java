package org.hrd._13_theam_kimhout_spring_homework003.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue's name cannot be empty!")
    @Length(max = 50, message = "Venue's name cannot exceed 50 characters!")
    private String venueName;
    @NotBlank(message = "Venue's location cannot be empty!")
    @Length(max = 50, message = "Venue's location's name cannot exceed 50 characters!")
    private String location;
}
