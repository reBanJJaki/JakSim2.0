CREATE TABLE LOGIN_LOG(
    L_ID INT(8) AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(30) NOT NULL,
    L_IP VARCHAR(255) NOT NULL,
    L_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID)
    ON DELETE CASCADE
);

COMMIT;