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
    id         INT PRIMARY KEY auto_increment,
    room_id    INT REFERENCES theme (room_id) NOT NULL,
    start_time TIME                           NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS reservations
(
    id              BINARY(16) PRIMARY KEY,
    scheduleId      INT REFERENCES schedule (id) NOT NULL,
    owner           VARCHAR                      NOT NULL,
    reservationDate DATE                         NOT NULL,
    phoneNumber     VARCHAR                      NOT NULL,
    numberOfPeople  INT                          NOT NULL,
    reservationAt   TIMESTAMP                    NOT NULL,
    amount          INT                          NOT NULL,
    payment_id      BINARY(16) COMMENT '결제 요청시 가맹점에서 채번하는 결제 아이디',
    imp_uid         VARCHAR COMMENT '결제 완료시 OnePort에서 채번 하는 결제 아이디',
    payment_state   VARCHAR,
    paid_at         TIMESTAMP
);