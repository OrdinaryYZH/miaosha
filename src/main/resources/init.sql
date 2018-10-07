CREATE TABLE IF NOT EXISTS miaosha_user (
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

CREATE TABLE IF NOT EXISTS good (
  id           BIGINT(20) NOT NULL AUTO_INCREMENT
  COMMENT '商品ID',
  goods_name   VARCHAR(16)         DEFAULT NULL
  COMMENT '商品名称',
  good_title   VARCHAR(64)         DEFAULT NULL
  COMMENT '商品标题',
  goods_img    VARCHAR(64)         DEFAULT NULL
  COMMENT '商品的图片',
  good_deta111 LONGTEXT COMMENT ' 商品的详情介绍 ',
  good_price   DECIMAL(10, 2)      DEFAULT 0.00
  COMMENT '商品单价',
  good_stock   INT(11)             DEFAULT '0'
  COMMENT '商品库存,-1表示没有限制',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COMMENT '商品表';

CREATE TABLE IF NOT EXISTS miaosha_good (
  id            BIGINT(20) NOT NULL AUTO_INCREMENT
  COMMENT '秒杀的商品表',
  good_id       BIGINT(20)          DEFAULT NULL
  COMMENT '商品id',
  miaosha_price DECIMAL(10, 2
                )                   DEFAULT 0.00
  COMMENT '秒杀价',
  stock_count   INT(11
                )                   DEFAULT NULL
  COMMENT '库存数量',
  start_date    DATETIME            DEFAULT NULL
  COMMENT '秒杀开始时间',
  end_date      DATETIME            DEFAULT NULL
  COMMENT '秒杀结束时间',
  PRIMARY KEY (id
  )
)
  ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COMMENT '秒杀商品表';

CREATE TABLE IF NOT EXISTS order_info (
  id              BIGINT(20) NOT NULL AUTO_INCREMENT,
  user_id         BIGINT(20)          DEFAULT NULL
  COMMENT '用户ID',
  good_id         BIGINT(20)          DEFAULT NULL
  COMMENT '商品 | D',
  delivery_addrid BIGINT(20)          DEFAULT NULL
  COMMENT '收货地址ID',
  good_name       VARCHAR(16)         DEFAULT NULL
  COMMENT '冗余过来的商品名称',
  good_count      INT(11)             DEFAULT 0
  COMMENT '商品数量',
  good_price      DECIMAL(10, 2)      DEFAULT 0.00
  COMMENT '商品单价',
  order_channel   TINYINT(4)          DEFAULT 0
  COMMENT '1:pc, 2:android, 3:ios',
  order_status    TINYINT(4)          DEFAULT 0
  COMMENT '订单状态,0新建未支付,1已支付,2已发货,3已收货,4已退款,5已完成',
  create_date     DATETIME            DEFAULT NULL
  COMMENT '订单的创建时间',
  pay_date        DATETIME            DEFAULT NULL
  COMMENT '支付时间',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COMMENT '订单表';

CREATE TABLE IF NOT EXISTS miaosha_order (
  id       BIGINT(20) NOT NULL AUTO_INCREMENT,
  user_id  BIGINT(20)          DEFAULT NULL
  COMMENT 'FBPID',
  order_id BIGINT(20)          DEFAULT NULL
  COMMENT '订单|D',
  good_id  BIGINT(20)          DEFAULT NULL
  COMMENT '商品|D',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COMMENT '秒杀订单表';

INSERT INTO USER (id, nickname, PASSWORD, salt, head, gmt_create, gmt_modified
) VALUES
  ('15812345678', 'test', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', NULL, now(
  ), now(
   )
  );

INSERT INTO `miaosha`.`good` (`goods_name`, `good_title`, `goods_img`, `good_deta111`, `good_price`, `good_stock`)
VALUES ('iPhoneX', 'iPhoneX', 'img/iphonex.png', '超级无敌的iPhoneX来了，快来买咯', '8888', '88')