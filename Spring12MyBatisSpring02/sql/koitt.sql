CREATE TABLE owner (
	owner_name		VARCHAR(30) PRIMARY KEY
	);
	
CREATE TABLE pet (
	pet_id		INT		NOT NULL	PRIMARY KEY AUTO_INCREMENT,
	pet_name 	VARCHAR(30),
	owner_name	VARCHAR(30),
	price		INT,
	birth_date	DATE,
	FOREIGN KEY(owner_name) REFERENCES owner(owner_name)
	);
	
INSERT INTO owner VALUES('LEE');
INSERT INTO owner VALUES('HONG');
INSERT INTO owner VALUES('PARK');
INSERT INTO owner VALUES('CHOI');
INSERT INTO owner VALUES('AHN');


INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('HAPPY', 'LEE', 1000, STR_TO_DATE('2018-02-10','%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('JJONG', 'HONG', 2000, STR_TO_DATE('2018-02-10','%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('NABI', 'PARK', 1340, STR_TO_DATE('2018-02-12','%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('MERRY', 'CHOI', 2340, STR_TO_DATE('2018-02-12','%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('BAMBINO', 'AHN', 1000, STR_TO_DATE('2018-02-12','%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('누렁이', 'LEE', 1000, STR_TO_DATE('2018-02-12','%Y-%m-%d'));
	
	
SELECT * FROM owner;
SELECT * FROM pet;
	
		