DROP TABLE IF EXISTS user;


CREATE TABLE user (
	id INT,
	username VARCHAR(20),
	password VARCHAR(20),
	age INT
);

INSERT INTO user (id, username, password, age)
	VALUES (1, 'USER-1', 'PASSWORD-1', 30);
INSERT INTO user (id, username, password, age)
	VALUES (2, 'USER-2', 'PASSWORD-2', 25);
INSERT INTO user (id, username, password, age)
	VALUES (3, 'USER-3', 'PASSWORD-3', 10);
INSERT INTO user (id, username, password, age)
	VALUES (4, 'USER-4', 'PASSWORD-4', 25);
INSERT INTO user (id, username, password, age)
	VALUES (5, 'USER-5', 'PASSWORD-5', 40);

SELECT * FROM user;
