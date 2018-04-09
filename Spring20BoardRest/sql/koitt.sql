#데이터베이스 인코딩 변경
ALTER DATABASE koitt CHARACTER SET utf8 COLLATE utf8_unicode_ci;

#DDL(Data Definition Language)
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS users_authority;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	no			INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	email		VARCHAR(255) NOT NULL,
	password	VARCHAR(255) NOT NULL,
	name		VARCHAR(255) NOT NULL,
	attachment	VARCHAR(255),
	UNIQUE (email)
);

# 사용자 권한 정의한 테이블
CREATE TABLE authority (
	id	INT NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);

# 사용자 번호와 사용자 권한 아이디값을 연결하는 테이블
CREATE TABLE users_authority (
	users_no INT NOT NULL,
	authority_id INT NOT NULL,
	FOREIGN KEY (users_no) REFERENCES users(no),
	FOREIGN KEY (authority_id) REFERENCES authority(id)
);

CREATE TABLE board (
	no INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(30) NOT NULL,
	content VARCHAR(255) NULL,
	user_no INT NOT NULL,
	regdate DATE NOT NULL,
	attachment VARCHAR(255),
	FOREIGN KEY (user_no) REFERENCES users(no)
);

#DML(Data Manipulation Language)

#권한 입력
INSERT INTO authority (id, name)
	VALUES (10, 'ADMIN');

INSERT INTO authority (id, name)
	VALUES (20, 'USER');

# 사용자 입력 (비밀번호는 1234)
INSERT INTO users (email, password, name, attachment)
	VALUES ('admin@gmail.com', 
	'$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', '관리자', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES ('user1@gmail.com',
	'$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', '김일반', NULL);
	
INSERT INTO users (email, password, name, attachment)
	VALUES ('user2@gmail.com',
	'$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', '김관사', NULL);

# 사용자에게 권한 부여
INSERT INTO users_authority VALUES (1, 10);	#관리자 사용자에게 관리자 권한 부여
INSERT INTO users_authority VALUES (2, 20);	#김일반 사용자에게 사용자 권한 부여
INSERT INTO users_authority VALUES (3, 10);	#김관사 사용자에게 관리자 권한 부여
INSERT INTO users_authority VALUES (3, 20);	#김관사 사용자에게 사용자 권한 부여

# 글 작성
INSERT INTO board (title, content, user_no, regdate, attachment)
	VALUES ('제목-1', '내용-1', 1, STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
	
INSERT INTO board (title, content, user_no, regdate, attachment)
	VALUES ('제목-2', '내용-2', 1, STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
	
INSERT INTO board (title, content, user_no, regdate, attachment)
	VALUES ('제목-3', '내용-3', 1, STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
	
#DQL(Data Query Language)
SELECT * FROM board;
SELECT * FROM users;
SELECT * FROM authority;
SELECT * FROM users_authority;


# 1. users_authority 테이블과 authority 테이블을 EQUI JOIN하는 SQL문
SELECT users_authority.users_no, authority.id, authority.name
FROM users_authority, authority
WHERE users_authority.authority_id = authority.id;

# 2. 1번에서 조인한 결과 테이블과 users 테이블을 EQUI JOIN하는 SQL문
SELECT u.no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname"
FROM users u,
	(SELECT users_authority.users_no, authority.id, authority.name
	FROM users_authority, authority
	WHERE users_authority.authority_id = authority.id) ua
WHERE u.no = ua.users_no;

# 3. 2번 결과에서 한 사용자에 대한 정보만 가져오는 SQL문 (2번 + AND u.no = #{no})
SELECT u.no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname"
FROM users u,
	(SELECT users_authority.users_no, authority.id, authority.name
	FROM users_authority, authority
	WHERE users_authority.authority_id = authority.id) ua
WHERE u.no = ua.users_no AND u.no = 3;

# 4. board 테이블과 users 테이블 EQUI JOIN
SELECT b.no, b.title, b.content, b.user_no, b.regdate, b.attachment, 
	u.email, u.name, u.attachment as "uattachment"
FROM board b, users u
WHERE b.user_no = u.no ORDER BY b.no DESC;


# 5. 4번 SQL문에서 하나의 게시물을 선택하기 위한 SQL문
SELECT b.no, b.title, b.content, b.user_no, b.regdate, b.attachment, 
  	u.email, u.name, u.attachment as "uattachment"
FROM board b, users u
WHERE b.user_no = u.no AND b.no = 10 ORDER BY b.no DESC;

# 6. 사용자 번호 3번 유저와 같이 관리자 권한과 일반사용자 권한을 함께 입력할 경우 (Oracle)
INSERT ALL
	INTO users_authority(users_no, authority_id)
  	VALUES (3, 10)
  	INTO users_authority(users_no, authority_id)
  	VALUES (3, 20)
SELECT * FROM DUAL;

# 6-1. 6번을 MySQL 버전으로 변경
INSERT INTO users_authority(users_no, authority_id) VALUES
(8, 10),
(8, 20);