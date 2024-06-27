DO $$
    BEGIN
        -- Check if the 'users' table exists
        IF NOT EXISTS (SELECT 1 FROM pg_tables WHERE tablename = 'users_roles' AND schemaname = 'public') THEN
            -- Create table if it does not exist
CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);

END IF;
END $$;