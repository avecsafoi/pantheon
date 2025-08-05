-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user
(
    id    BIGINT NOT NULL COMMENT '사용자ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '이름',
    age   INT NULL DEFAULT NULL COMMENT '나이',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '이메일주소',
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS children;

CREATE TABLE children
(
    id      BIGINT NOT NULL COMMENT '어린이ID',
    name    VARCHAR(30) NULL DEFAULT NULL COMMENT '어린이이름',
    user_id BIGINT NULL DEFAULT NULL COMMENT '부모ID',
    PRIMARY KEY (id)
);