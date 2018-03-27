/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : cas

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2018-03-26 18:46:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `name` varchar(32) NOT NULL COMMENT '权限名称',
  `resource_type` varchar(32) NOT NULL COMMENT '资源类型，[menu|button]',
  `url` varchar(32) NOT NULL COMMENT '资源路径',
  `permission` varchar(32) DEFAULT NULL COMMENT '//权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(32) DEFAULT NULL COMMENT '父编号列表 不确定是否需要这样设计',
  `disabled` tinyint(1) NOT NULL COMMENT '是否失效 0否1是',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息表';

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `role` varchar(32) NOT NULL COMMENT '角色标识 如admin 唯一',
  `description` varchar(32) NOT NULL COMMENT '角色描述 用户页面显示',
  `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否失效 0否 1是',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `permission` varchar(32) NOT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联信息表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `user_name` varchar(32) NOT NULL COMMENT '用户账号名称 唯一',
  `name` varchar(32) DEFAULT NULL COMMENT '用户昵称或者真实名称',
  `password` varchar(32) NOT NULL COMMENT '密码 MD5加密后保存',
  `salt` varchar(32) DEFAULT NULL COMMENT '加密密码的盐',
  `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否失效 0否 1是',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联信息表';
