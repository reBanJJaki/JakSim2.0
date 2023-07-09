DROP TABLE REVIEW;
DROP TABLE REVIEW_IMAGE;

CREATE TABLE REVIEW(
    R_IDX INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(30) NOT NULL,
    UT_IDX INT(8) NOT NULL,
    R_CONTENT VARCHAR(1000) NOT NULL,
    R_STAR INT(1) NOT NULL DEFAULT '3',
    R_C_DT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    R_M_DT TIMESTAMP,
    FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) ON DELETE CASCADE,
    FOREIGN KEY(UT_IDX) REFERENCES TRAINER_DETAILS(UT_IDX) ON DELETE CASCADE
);

CREATE TABLE REVIEW_IMAGE(
    RI_IDX INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    R_IDX INT(8) NOT NULL,
    RI_PATH VARCHAR(1000) NOT NULL,
    FOREIGN KEY (R_IDX) REFERENCES REVIEW(R_IDX) ON DELETE CASCADE
);

COMMIT;

INSERT INTO REVIEW
VALUES(NULL, 'test', 1, '트레이너가 친절하고 PT가 맛있어요', 5, current_date, NULL);
INSERT INTO REVIEW
VALUES(NULL, 'humble', 1, '이렇게 피티가 뭐같은적이 없음', 1, current_date, NULL);
INSERT INTO REVIEW
VALUES(NULL, 'test96', 1, '무릎이 박살남', 2, current_date, NULL);

INSERT INTO REVIEW_IMAGE
VALUES(NULL, 1, 'files/review/1.jpg');
INSERT INTO REVIEW_IMAGE
VALUES(NULL, 2, 'files/review/2.jpg');
INSERT INTO REVIEW_IMAGE
VALUES(NULL, 4, 'files/review/3.jpg');