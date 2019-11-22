DROP TABLE person IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE person (
  id_person INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(45),
  surname VARCHAR(45),
  email VARCHAR(45),
  phone VARCHAR(45),
  user_id INTEGER NOT NULL
);

CREATE TABLE users (
  id INTEGER IDENTITY PRIMARY KEY,
  user_name VARCHAR(45),
  password VARCHAR(45),
  roles VARCHAR(45),
  active BOOLEAN
);