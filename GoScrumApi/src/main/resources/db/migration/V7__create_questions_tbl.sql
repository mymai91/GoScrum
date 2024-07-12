CREATE TABLE questions
(
    id        UUID NOT NULL,
    title     VARCHAR(255),
    detail    VARCHAR(255),
    is_active BOOLEAN,
    "order"   INTEGER,
    room_id   UUID,
    CONSTRAINT pk_questions PRIMARY KEY (id)
);

ALTER TABLE questions
    ADD CONSTRAINT FK_QUESTIONS_ON_ROOM FOREIGN KEY (room_id) REFERENCES rooms (id);