CREATE OR REPLACE TABLE theme
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

CREATE OR REPLACE TABLE schedule
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    room_id    INT  NOT NULL,
    start_time TIME NOT NULL,
    UNIQUE (room_id, start_time),
    FOREIGN KEY (room_id) REFERENCES theme (room_id)
);



CREATE OR REPLACE TABLE reservations
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


CREATE OR REPLACE TABLE `board` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `author` varchar(255) NOT NULL,
                         `title` varchar(255) NOT NULL,
                         `content` varchar(255) NOT NULL,
                         `created_date` datetime DEFAULT NULL,
                         `modified_date` datetime DEFAULT NULL,
                         PRIMARY KEY (`id`)
);

# 지금은 이래도 되지만 실제 운영 서버에서는 OR REPLACE나 IF NOT EXIST로 DDL을 관리하는 건 위험해요.