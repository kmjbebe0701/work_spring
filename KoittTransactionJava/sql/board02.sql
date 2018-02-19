CREATE TABLE board02 (
	no INT PRIMARY KEY,
	title VARCHAR(30),
	content VARCHAR(255),
	writer VARCHAR(30),
	regdate DATE
);
DROP TABLE board02;

INSERT INTO board02 (no, title, content, writer, regdate)
	VALUES (1, 'TITLE-1', 'CONTENT-1', 'WRITER-1', CURDATE());
INSERT INTO board02 (no, title, content, writer, regdate)
	VALUES (2, 'TITLE-2', 'CONTENT-2', 'WRITER-2', CURDATE());


#데이터 베이스에서 최근 글의 글 번호를 가져오는 SQL문 
SELECT no FROM board02 ORDER BY no DESC LIMIT 1;

SELECT * FROM board02;