DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL
);

INSERT INTO employee (name, age) VALUES
  ('sample1', 1),
  ('sample2', 2);