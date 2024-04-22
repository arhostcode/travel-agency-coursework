--liquibase formatted sql

--changeset arhostcode:1
CREATE TABLE IF NOT EXISTS tour_place
(
    id          uuid primary key default gen_random_uuid(),
    name        TEXT NOT NULL,
    description TEXT NOT NULL,
    city        TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS hotel
(
    id          uuid primary key default gen_random_uuid(),
    name        TEXT             NOT NULL,
    description TEXT             NOT NULL,
    city        TEXT             NOT NULL,
    location    TEXT             NOT NULL,
    image       TEXT             NOT NULL,
    price       DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS flight
(
    id          uuid primary key default gen_random_uuid(),
    from_place  TEXT             NOT NULL,
    to_place    TEXT             NOT NULL,
    start_date  TIMESTAMP WITH TIME ZONE,
    finish_date TIMESTAMP WITH TIME ZONE,
    price       DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS tour
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        TEXT NOT NULL,
    description TEXT NOT NULL,
    start_date  TIMESTAMP WITH TIME ZONE,
    finish_date TIMESTAMP WITH TIME ZONE,
    tour_place  UUID REFERENCES tour_place (id),
    hotel       UUID REFERENCES hotel (id),
    flight      UUID REFERENCES flight (id)
);

CREATE TABLE IF NOT EXISTS user_entity
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name TEXT    NOT NULL,
    email     TEXT    NOT NULL,
    password  TEXT    NOT NULL,
    is_admin  BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS user_tour
(
    user_id UUID REFERENCES user_entity (id),
    tour_id UUID REFERENCES tour (id),
    PRIMARY KEY (user_id, tour_id)
);

CREATE TABLE IF NOT EXISTS user_flight
(
    user_id   UUID REFERENCES user_entity (id),
    flight_id UUID REFERENCES flight (id),
    PRIMARY KEY (user_id, flight_id)
);

CREATE TABLE IF NOT EXISTS user_hotel
(
    user_id  UUID REFERENCES user_entity (id),
    hotel_id UUID REFERENCES hotel (id),
    PRIMARY KEY (user_id, hotel_id)
);

--rollback drop table tour, tour_place, hotel, flight;
