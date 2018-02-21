
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

INSERT INTO Book (title, author, publisher, price, description, attachment)
	VALUES ('제목-1', '저자-1', '출판사-1', 100, '소개-1', null);
	
INSERT INTO Book (title, author, publisher, price, description, attachment)
	VALUES ('제목-2', '저자-2', '출판사-2', 200, '소개-2', null);
	
INSERT INTO Book (title, author, publisher, price, description, attachment)
	VALUES ('제목-3', '저자-3', '출판사-3', 300, '소개-3', null);
	
	
SELECT * FROM book ;