drop table  if EXISTS user_login_info;
create table user_login_info(
	id BIGINT UNSIGNED not null auto_increment COMMENT '主键',
	username VARCHAR(100) not null COMMENT '登录账号',
	pwd VARCHAR(100) not null COMMENT '密码',
	last_login  datetime not null  COMMENT '上次登录时间',
	last_login_ip  int(4) not null COMMENT '上次登录ip',
	PRIMARY KEY(`id`)
);


drop table  if EXISTS sys_role;
create table sys_role(
	id BIGINT UNSIGNED not null auto_increment COMMENT '主键',
	name VARCHAR(100) not null COMMENT '角色名称',
	PRIMARY KEY(`id`)
);

drop table  if EXISTS sys_user_role;
create table sys_user_role(
	id BIGINT UNSIGNED not null auto_increment comment '主键',
	user_id BIGINT UNSIGNED not null COMMENT '用户id',
	role_id BIGINT UNSIGNED not null COMMENT '角色id',
	PRIMARY KEY(`id`)
);

DROP TABLE IF EXISTS `sys_permission`;
create table sys_permission(
	id BIGINT UNSIGNED not null auto_increment comment '主键',
	name VARCHAR(200) NOT NULL COMMENT '权限名称',
	description VARCHAR(200) not null DEFAULT '无' comment '权限描述',
	url VARCHAR(200) NOT NULL comment '路径',
	pid BIGINT DEFAULT NULL,
	PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role`(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT UNSIGNED NOT NULL,
  `permission_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
);