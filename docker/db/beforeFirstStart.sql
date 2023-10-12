CREATE TABLE IF NOT EXISTS plants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    mos VARCHAR(255),
    pump INTEGER,
    last VARCHAR(255),
    avg VARCHAR(255),
    pb  TEXT
);

select * from plants;
