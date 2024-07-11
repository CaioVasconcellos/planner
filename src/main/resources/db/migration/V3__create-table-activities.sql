CREATE TABLE activities(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    occurs_at TIMESTAMP NOT NULL,
    trip_activities_id UUID,
    FOREIGN KEY (trip_activities_id) REFERENCES trips(id) ON DELETE CASCADE
);