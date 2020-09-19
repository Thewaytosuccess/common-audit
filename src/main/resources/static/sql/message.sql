drop table if exists message;

create table message(
id varchar(32) not null comment '消息id',
body varchar(128) not null comment '消息内容',
send_time datetime not null default current_timestamp comment '发送时间',
receiver_id bigint(20) not null comment '接收人id',
biz_type tinyint unsigned not null default 0 comment '业务类型',
biz_id bigint(20) unsigned not null comment '业务id',
primary key (id),
key idx_receiver_id(receiver_id),
key idx_biz_id(biz_id)
)engine = InnoDB default charset utf8mb4 comment '消息表';

create table user_info(
  `id` bigint unsigned not null comment '主键',
  `username` varchar(20) not null comment '用户名',
  `password` varchar(32) not null comment '密码',
  `email` varchar(20) not null comment '邮箱',
  `phone` varchar(16) not null comment '手机',
  `status` bit(1) NOT NULL DEFAULT b'1' COMMENT '状态值：1启用，0禁用',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除 0正常，1已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表'
