CREATE TABLE IF NOT EXISTS theme
(
    room_id        INT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    description    VARCHAR(255) NOT NULL,
    difficulty     INT          NOT NULL,
    alt_difficulty VARCHAR(255),
    estimated_time VARCHAR(255) NOT NULL,
    price          INT          NOT NULL,
    genre          VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS schedule
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    room_id    INT  NOT NULL,
    start_time TIME NOT NULL,
    UNIQUE (room_id, start_time),
    FOREIGN KEY (room_id) REFERENCES theme (room_id)
);



CREATE TABLE IF NOT EXISTS reservations
(
    id               BINARY(16) PRIMARY KEY,
    schedule_id      INT          NOT NULL,
    owner            VARCHAR(255) NOT NULL,
    reservation_date DATE         NOT NULL,
    phone_number     VARCHAR(255) NOT NULL,
    number_of_people INT          NOT NULL,
    reservation_at   TIMESTAMP    NOT NULL,
    amount           INT          NOT NULL,
    payment_id       BINARY(16) COMMENT '결제 요청시 가맹점에서 채번하는 결제 아이디',
    imp_uid          VARCHAR(255) COMMENT '결제 완료시 OnePort에서 채번 하는 결제 아이디',
    state            VARCHAR(255),
    paid_at          TIMESTAMP,
    receipt_url      VARCHAR(2047),
    FOREIGN KEY (schedule_id) REFERENCES schedule (id)
);