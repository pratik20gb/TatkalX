CREATE TABLE stations (
                          id BIGSERIAL PRIMARY KEY,
                          station_code VARCHAR(10) NOT NULL UNIQUE,
                          station_name VARCHAR(100) NOT NULL,
                          city VARCHAR(100) NOT NULL,
                          state VARCHAR(100) NOT NULL,
                          created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Seed initial popular railway stations
INSERT INTO stations (station_code, station_name, city, state) VALUES
                                                                   ('NDLS', 'New Delhi', 'New Delhi', 'Delhi'),
                                                                   ('HWH', 'Howrah Junction', 'Kolkata', 'West Bengal'),
                                                                   ('MMCT', 'Mumbai Central', 'Mumbai', 'Maharashtra'),
                                                                   ('BPL', 'Bhopal Junction', 'Bhopal', 'Madhya Pradesh'),
                                                                   ('CNB', 'Kanpur Central', 'Kanpur', 'Uttar Pradesh'),
                                                                   ('LKO', 'Lucknow Charbagh', 'Lucknow', 'Uttar Pradesh');