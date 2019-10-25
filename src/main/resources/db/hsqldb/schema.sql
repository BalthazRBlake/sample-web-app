DROP TABLE person IF EXISTS;

CREATE TABLE person (
  id_person INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(45),
  surname VARCHAR(45),
  email VARCHAR(45),
  phone VARCHAR(45)
);
