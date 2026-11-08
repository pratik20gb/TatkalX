CREATE TABLE train_schedules (
                                 id BIGSERIAL PRIMARY KEY,
                                 train_id BIGINT NOT NULL REFERENCES trains(id) ON DELETE CASCADE,
                                 station_id BIGINT NOT NULL REFERENCES stations(id),
                                 stop_order INT NOT NULL,
                                 arrival_time TIME,
                                 departure_time TIME,
                                 halt_duration_minutes INT NOT NULL DEFAULT 0,
                                 distance_from_source_km INT NOT NULL DEFAULT 0,
                                 created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                 CONSTRAINT uq_train_stop_order UNIQUE (train_id, stop_order),
                                 CONSTRAINT uq_train_station UNIQUE (train_id, station_id)
);

-- Seed route for Train 12002 (New Delhi -> Kanpur -> Bhopal) if it exists
INSERT INTO train_schedules (train_id, station_id, stop_order, arrival_time, departure_time, halt_duration_minutes, distance_from_source_km)
SELECT t.id, s.id, 1, NULL, '06:00:00', 0, 0
FROM trains t, stations s
WHERE t.train_number = '12002' AND s.station_code = 'NDLS';

INSERT INTO train_schedules (train_id, station_id, stop_order, arrival_time, departure_time, halt_duration_minutes, distance_from_source_km)
SELECT t.id, s.id, 2, '10:50:00', '10:55:00', 5, 440
FROM trains t, stations s
WHERE t.train_number = '12002' AND s.station_code = 'CNB';

INSERT INTO train_schedules (train_id, station_id, stop_order, arrival_time, departure_time, halt_duration_minutes, distance_from_source_km)
SELECT t.id, s.id, 3, '14:30:00', NULL, 0, 700
FROM trains t, stations s
WHERE t.train_number = '12002' AND s.station_code = 'BPL';