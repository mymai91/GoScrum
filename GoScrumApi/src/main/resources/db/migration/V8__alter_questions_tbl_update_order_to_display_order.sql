ALTER TABLE questions
    ADD display_order INTEGER;

ALTER TABLE questions
    DROP
        COLUMN "order";