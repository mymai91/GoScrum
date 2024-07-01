CREATE TABLE rooms
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    slug        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    is_active   BOOLEAN      NOT NULL,
    is_private  BOOLEAN      NOT NULL,
    password    VARCHAR(255),
    host_id     BIGINT       NOT NULL,
    CONSTRAINT pk_rooms PRIMARY KEY (id)
);

ALTER TABLE rooms
    ADD CONSTRAINT uc_53fc929d19bc8c55c9911799e UNIQUE (slug);

ALTER TABLE rooms
    ADD CONSTRAINT FK_ROOMS_ON_HOST FOREIGN KEY (host_id) REFERENCES users (id);