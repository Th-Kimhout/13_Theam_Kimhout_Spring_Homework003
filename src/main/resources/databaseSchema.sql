CREATE TABLE attendees
(
    attendee_id    serial PRIMARY KEY,
    attendee_name  varchar(50) NOT NULL,
    attendee_email varchar(50) NOT NULL
);

CREATE TABLE venues
(
    venue_id   serial PRIMARY KEY,
    venue_name varchar(50) NOT NULL,
    location   varchar(50) NOT NULL
);

CREATE TABLE events
(
    event_id   serial PRIMARY KEY,
    event_name varchar(50)                                                          NOT NULL,
    event_date timestamp                                                            NOT NULL,
    venue_id   int REFERENCES venues (venue_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);

CREATE TABLE event_attendee
(
    id          serial PRIMARY KEY,
    event_id    int REFERENCES events (event_id) ON DELETE CASCADE ON UPDATE CASCADE       NOT NULL,
    attendee_id int REFERENCES attendees (attendee_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);