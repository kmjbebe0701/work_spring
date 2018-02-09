DROP TABLE emp;
DROP TABLE dept;

CREATE TABLE dept(
  deptno INT(2),
  dname  VARCHAR(14),
  loc    VARCHAR(13),
  PRIMARY KEY (deptno)
);
 
CREATE TABLE emp(
  empno    INT(4),
  ename    VARCHAR(10),
  job      VARCHAR(9),
  mgr      INT(4),
  hiredate DATE,
  sal      INT(7),
  comm     INT(7),
  deptno   INT(2),
  PRIMARY KEY (empno),
  FOREIGN KEY (deptno) REFERENCES dept (deptno)
);