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