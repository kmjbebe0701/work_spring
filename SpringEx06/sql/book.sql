
DROP TABLE Book;

CREATE TABLE Book(
		isbn				INT				NOT NULL		 PRIMARY KEY AUTO_INCREMENT,
		title				VARCHAR(30)		NOT NULL,
		author				VARCHAR(10)		NOT NULL,
		publisher			VARCHAR(30)		NOT NULL,
		price				INT				NOT NULL,
		description			VARCHAR(255)		NULL,
		attachment			VARCHAR(255)
);

CREATE TABLE users (
	no			INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	email		VARCHAR(255) NOT NULL,
	password	VARCHAR(255) NOT NULL,
	name		VARCHAR(255) NOT NULL,
	attachment VARCHAR(255),
	UNIQUE (email)
);

INSERT INTO Book (title, author, publisher, price, description, attachment)
	VALUES ('제목-1', '저자-1', '출판사-1', 100, '소개-1', null);
	
INSERT INTO Book (title, author, publisher, price, description, attachment)
	VALUES ('제목-2', '저자-2', '출판사-2', 200, '소개-2', null);
	
INSERT INTO Book (title, author, publisher, price, description, attachment)
	VALUES ('제목-3', '저자-3', '출판사-3', 300, '소개-3', null);
	
INSERT INTO users (email, password, name, attachment)
	VALUES ('teachertophoon@gmail.com', '1234', '정상훈', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES ('gildong@gmail.com', '5678', '홍길동', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES ('younghee@gmail.com', '7788', '김영희', NULL);
	
	
	
SELECT * FROM book ;
SELECT * FROM users;