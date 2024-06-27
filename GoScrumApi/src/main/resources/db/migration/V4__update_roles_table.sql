DO $$
BEGIN
    -- Check if the 'name' column does not exist before adding it
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'roles'
        AND column_name = 'name'
    ) THEN
ALTER TABLE roles
    ADD name VARCHAR(255);
 END IF;

    -- Check if the 'role' column exists before dropping it
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'roles'
        AND column_name = 'role'
    ) THEN
ALTER TABLE roles
DROP
COLUMN role;

    END IF;
END $$;