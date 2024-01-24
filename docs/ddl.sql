CREATE TABLE IF NOT EXISTS theme
(
    room_id        INT PRIMARY KEY,
    name           VARCHAR NOT NULL,
    description    VARCHAR NOT NULL,
    difficulty     INT     NOT NULL,
    alt_difficulty VARCHAR,
    estimatedTime  VARCHAR NOT NULL,
    price          INT     NOT NULL,
    genre          VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS schedule
(
    id        INT PRIMARY KEY auto_increment,
    room_id   INT REFERENCES theme (room_id) NOT NULL,
    start_time TIME                           NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS reservations
(
    id              BINARY(16) PRIMARY KEY auto_increment,
    scheduleId      INT REFERENCES schedule (room_id) NOT NULL,
    owner           VARCHAR                           NOT NULL,
    reservationDate DATE                              NOT NULL,
    phoneNumber     VARCHAR                           NOT NULL,
    numberOfPeople  INT                               NOT NULL,
    reservationAt   DATE
)