CREATE TABLE users_rooms
(
    room_id UUID   NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_users_rooms PRIMARY KEY (room_id, user_id)
);

ALTER TABLE users_rooms
    ADD CONSTRAINT fk_useroo_on_room FOREIGN KEY (room_id) REFERENCES rooms (id);

ALTER TABLE users_rooms
    ADD CONSTRAINT fk_useroo_on_user FOREIGN KEY (user_id) REFERENCES users (id);