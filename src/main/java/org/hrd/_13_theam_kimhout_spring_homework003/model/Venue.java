package org.hrd._13_theam_kimhout_spring_homework003.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private Integer venueId;
    private String venueName;
    private String location;
}
