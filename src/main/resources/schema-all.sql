DROP TABLE people IF EXISTS;

CREATE TABLE employees (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    age int,
    employee_id VARCHAR(20),
    position VARCHAR(20)
);