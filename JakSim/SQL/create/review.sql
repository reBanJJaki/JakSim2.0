DROP TABLE REVIEW;

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

alter table review drop TRAINER_ID;

COMMIT;

INSERT INTO REVIEW
VALUES(NULL, 'ujeong', 'test93', 4, '트레이너가 친절하고 PT가 맛있어요', 5, '2023-06-27 12:34:56', NULL);
INSERT INTO REVIEW
VALUES(NULL, 'humble', 'test93', 4, '무릎이 박살남', 1, '2023-07-01 12:34:56', NULL);
INSERT INTO REVIEW
VALUES(NULL, 'hyerin', 'test93', 4, '나쁘지 않아용', 3, '2023-07-07 12:34:56', NULL);
INSERT INTO REVIEW
VALUES(NULL, 'hye8997', 'test93', 4, '피티는 별론데 트레이너가 잘생겼어요~', 4, current_date, NULL);
INSERT INTO REVIEW
VALUES(NULL, 'senny9', 'test93', 4, '체력이 좋아졌어요!', 5, current_date, NULL);
INSERT INTO REVIEW
VALUES(NULL, 'gse2881', 'test93', 4, '잘 가르쳐 주시고 친절해요 ㅎㅎ', 5, current_date, NULL);
INSERT INTO REVIEW
VALUES(NULL, 'test1', 'test93', 4, '또 배우고 싶습니당', 5, current_date, NULL);
INSERT INTO REVIEW
VALUES(NULL, 'test2', 'test93', 4, '시설도 좋고 트레이너쌤도 친절해요.', 5, current_date, NULL);

INSERT INTO REVIEW
VALUES(NULL, 'test96', 'test93', 4, '트레이너쌤이 불친절해요. 근데 잘 가르쳐주는듯.', 3, current_date, NULL);