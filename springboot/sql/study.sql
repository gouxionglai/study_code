DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(64)  DEFAULT NULL comment '姓名',
  `email` varchar(64)   DEFAULT NULL comment '邮箱',
  `gender` tinyint DEFAULT 0 comment '性别 0女 1男  默认0',
  `department` integer DEFAULT NULL comment '部门',
  `birth` datetime DEFAULT NULL comment '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin comment '雇员表';



DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(64)  DEFAULT NULL comment '部门名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin comment '部门表';