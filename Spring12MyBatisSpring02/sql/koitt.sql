CREATE TABLE owner (
	owner_name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE pet (
	pet_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	pet_name VARCHAR(30),
	owner_name VARCHAR(30),
	price INT,
	birth_date DATE,
	FOREIGN KEY (owner_name) REFERENCES owner(owner_name)
);

INSERT INTO owner VALUES('LEE');
INSERT INTO owner VALUES('HONG');
INSERT INTO owner VALUES('PARK');
INSERT INTO owner VALUES('CHOI');
INSERT INTO owner VALUES('AHN');

INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('HAPPY', 'LEE', 1000, STR_TO_DATE('2018-02-10', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('JJONG', 'HONG', 2000, STR_TO_DATE('2018-02-10', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('NABI', 'PARK', 1340, STR_TO_DATE('2018-02-12', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('MERRY', 'CHOI', 2340, STR_TO_DATE('2018-02-12', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('BAMBINO', 'AHN', 1000, STR_TO_DATE('2018-02-12', '%Y-%m-%d'));
	
INSERT INTO pet (pet_name, owner_name, price, birth_date)
	VALUES('누렁이', 'LEE', 1000, STR_TO_DATE('2018-02-12', '%Y-%m-%d'));


SELECT * FROM owner;
SELECT * FROM pet;

# pet 테이블과 owner 테이블을 조인한 후
# owner_name이 PARK이고, pet_name이 NABI인 행의 모든 정보를 출력하라.
SELECT * FROM pet p, owner o
	WHERE p.owner_name = o.owner_name
	AND p.pet_name = 'NABI'
	AND o.owner_name = 'PARK';

# pet 테이블과 owner 테이블을 조인한 후
# owner_name이 HONG인 행의 모든 정보를 출력하라.
SELECT * FROM owner o, pet p
	WHERE o.owner_name = p.owner_name
	AND o.owner_name = 'HONG';












