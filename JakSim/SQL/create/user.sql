DROP TABLE USER_INFO;

CREATE TABLE USER_INFO(
                          USER_ID VARCHAR(30),
                          USER_PW VARCHAR(30) NOT NULL,
                          USER_NAME VARCHAR(50) NOT NULL,
                          USER_GENDER INT(1) NOT NULL,
                          USER_TEL VARCHAR(15) UNIQUE NOT NULL,
                          USER_QUESTION INT(1) NOT NULL,
                          USER_ANSWER VARCHAR(50) NOT NULL,
                          USER_BIRTH DATE NOT NULL,
                          USER_C_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          USER_ROLE INT(1) NOT NULL,
                          PRIMARY KEY (USER_ID)
);

COMMIT;

INSERT INTO USER_INFO
VALUES('wkdgyfla97', '1234', '장효림', '0', '01085945142', '0', '대구광역시', '1997-03-30', '2023-06-09 12:36:34', 1);
INSERT INTO USER_INFO
VALUES('hye8997', '1234', '정혜화', '1', '01012345678', '0', '전라남도 해남군', '1989-03-30', current_timestamp, 1);
INSERT INTO USER_INFO
VALUES('ujeong', '1004', '남유정', '1', '01012345999', '0', '서울특별시', '2000-05-28', current_timestamp, 1);