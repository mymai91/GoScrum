INSERT INTO roles (name) VALUES ('ROLE_USER') ON DUPLICATE KEY UPDATE name=name;
INSERT INTO roles (name) VALUES ('ROLE_ADMIN') ON DUPLICATE KEY UPDATE name=name;
INSERT INTO roles (name) VALUES ('ROLE_ANONYMOUS') ON DUPLICATE KEY UPDATE name=name;
