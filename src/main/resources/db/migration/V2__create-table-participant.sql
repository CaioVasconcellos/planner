CREATE TABLE participants(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    is_confirmed BOOLEAN NOT NULL,
    trip_participants_id UUID,
    FOREIGN KEY(trip_participants_id) REFERENCES trips(id) ON DELETE CASCADE
);