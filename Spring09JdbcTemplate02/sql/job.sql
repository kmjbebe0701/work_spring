DROP TABLE job;

CREATE TABLE job (
	job_id		VARCHAR(10) PRIMARY KEY,
	job_title VARCHAR(10),
	max_salary INT,
	min_salary INT
);

SELECT * FROM job;