
CREATE TABLE worker (
id_worker IDENTITY PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2),
birthday DATE CHECK (birthday >= '1900-01-01'),
level VARCHAR(100) NOT NULL CHECK level IN ('Trainee', 'Junior', 'Middle', 'Senior'),
salary INT CHECK (salary >= 100 AND salary <= 100000)
)

CREATE TABLE client (
id_client IDENTITY PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2)
)

CREATE TABLE project (
id_project IDENTITY PRIMARY KEY,
client_id BIGINT NOT NULL,
start_date DATE,
finish_date DATE)


CREATE TABLE project_worker (
project_id BIGINT NOT NULL,
worker_id BIGINT NOT NULL,
PRIMARY KEY(project_id, worker_id),
FOREIGN KEY(project_id) REFERENCES project(id_project),
FOREIGN KEY(worker_id) REFERENCES worker(id_worker)
)