CREATE TABLE train_coaches (
                               id BIGSERIAL PRIMARY KEY,
                               train_id BIGINT NOT NULL REFERENCES trains(id) ON DELETE CASCADE,
                               coach_number VARCHAR(10) NOT NULL,
                               coach_type VARCHAR(10) NOT NULL,
                               total_seats INT NOT NULL,
                               created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT uq_train_coach UNIQUE (train_id, coach_number),
                               CONSTRAINT chk_total_seats_positive CHECK (total_seats > 0)
);

CREATE INDEX idx_train_coaches_train_id ON train_coaches(train_id);
CREATE INDEX idx_train_coaches_type ON train_coaches(coach_type);

-- Seed coaches for Train 12002 (Bhopal Shatabdi / Express composition)
INSERT INTO train_coaches (train_id, coach_number, coach_type, total_seats)
SELECT t.id, 'A1', '2A', 48
FROM trains t
WHERE t.train_number = '12002';

INSERT INTO train_coaches (train_id, coach_number, coach_type, total_seats)
SELECT t.id, 'B1', '3A', 72
FROM trains t
WHERE t.train_number = '12002';

INSERT INTO train_coaches (train_id, coach_number, coach_type, total_seats)
SELECT t.id, 'B2', '3A', 72
FROM trains t
WHERE t.train_number = '12002';

INSERT INTO train_coaches (train_id, coach_number, coach_type, total_seats)
SELECT t.id, 'S1', 'SL', 72
FROM trains t
WHERE t.train_number = '12002';