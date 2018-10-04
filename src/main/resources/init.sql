CREATE TABLE IF NOT EXISTS user (
  id           VARCHAR(16) NOT NULL
  COMMENT
    '手机',
  nickname     VARCHAR(255),
  PASSWORD     VARCHAR(32) NOT NULL,
  salt         VARCHAR(10) NOT NULL,
  head         VARCHAR(255),
  gmt_create   DATETIME    NOT NULL,
  gmt_modified DATETIME    NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT '用户表';

INSERT INTO user (id, nickname, PASSWORD, salt, head, gmt_create, gmt_modified) VALUES
  ('15812345678', 'test', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', NULL, now(), now())