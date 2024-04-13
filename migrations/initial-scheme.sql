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
    price       DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS flight
(
    id          uuid primary key default gen_random_uuid(),
    name        TEXT             NOT NULL,
    description TEXT             NOT NULL,
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


--rollback drop table tour, tour_place, hotel, flight;
