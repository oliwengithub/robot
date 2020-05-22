/*
 Navicat Premium Data Transfer

 Source Server         : testlocal
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : robot

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 22/05/2020 18:10:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_auth
-- ----------------------------
DROP TABLE IF EXISTS `system_auth`;
CREATE TABLE `system_auth`  (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `user_id` int(11) NOT NULL COMMENT '权限ID列表',
  `role_ids` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `admin_id` int(11) NOT NULL DEFAULT 0,
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`auth_id`) USING BTREE,
  UNIQUE INDEX `UNIQ_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_auth
-- ----------------------------
INSERT INTO `system_auth` VALUES (1, 1, '1', 1, 'admin', '2019-01-24 14:32:36');
INSERT INTO `system_auth` VALUES (2, 2, '2', 1, 'admin', '2019-01-24 15:13:48');
INSERT INTO `system_auth` VALUES (3, 3, '2', 1, 'admin', '2020-01-07 21:04:45');

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数值',
  `admin_id` int(11) NOT NULL DEFAULT 0,
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_f_code_name`(`name`) USING BTREE,
  INDEX `idx_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES (1, 'sys_conf', '系统配置', '{\"name\":\"后台管理系统\",\"gateway\":\"#\",\"customer\":\"图灵科技有限公司\",\"author\":\"图灵技术团队\",\"mobile\":\"18897956300\",\"copyright\":\"© 2019 <a href=\\\"#\\\">图灵科技有限公司</a> 版权所有\"}', 1, 'admin', '2018-01-22 00:00:00');
INSERT INTO `system_config` VALUES (2, 'email_conf', '邮箱配置', '{\"host_name\":\"smtp.139.com\",\"port\":\"465\",\"user_name\":\"oliwens@163.com\",\"name\":\"图灵科技有限公司\",\"password\":\"aoliwen@163.com\"}', 1, 'admin', '2018-01-22 00:00:00');

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父级菜单ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单链接',
  `type` int(1) NULL DEFAULT NULL COMMENT '菜单类型：0、菜单；1、按钮；2、操作',
  `tag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作按钮的tag',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '菜单排序，越小越前面',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态：0正常，1删除',
  `admin_id` int(11) NOT NULL DEFAULT 0,
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_type`(`type`) USING BTREE,
  INDEX `IDX_pid`(`pid`) USING BTREE,
  INDEX `IDX_sort`(`sort`) USING BTREE,
  INDEX `IDX_status`(`status`) USING BTREE,
  INDEX `IDX_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (1, 0, '权限管理', '', 0, '', 'layui-icon-auz', 32, 0, 1, 'admin', '2019-01-25 14:50:53');
INSERT INTO `system_menu` VALUES (2, 1, '用户管理', '/system/user/list', 0, NULL, 'layui-icon-username', 1, 0, 1, 'admin', '2019-01-25 14:51:28');
INSERT INTO `system_menu` VALUES (3, 1, '菜单管理', '/system/menu/list', 0, '', 'layui-icon-template-1', 2, 0, 1, 'admin', '2019-01-25 14:52:02');
INSERT INTO `system_menu` VALUES (4, 1, '角色管理', '/system/role/list', 0, '', 'layui-icon-find-fill', 3, 0, 1, 'admin', '2019-01-25 14:52:40');
INSERT INTO `system_menu` VALUES (5, 2, '用户列表', '/system/user/list/query', 2, '', 'layui-icon-align-left', 1, 0, 1, 'admin', '2019-02-13 17:45:15');
INSERT INTO `system_menu` VALUES (6, 2, '添加用户', '/system/user/insert,/system/user/add', 1, 'add', 'layui-icon-add-1', 2, 0, 1, 'admin', '2019-01-25 14:55:34');
INSERT INTO `system_menu` VALUES (7, 2, '修改用户', '/system/user/edit,/system/user/update,/system/user/update/status', 1, 'edit', 'layui-icon-edit', 3, 0, 1, 'admin', '2019-01-25 17:58:09');
INSERT INTO `system_menu` VALUES (8, 2, '删除用户', '/system/user/delete', 1, 'delete', 'layui-icon-delete', 4, 0, 1, 'admin', '2019-01-25 18:00:32');
INSERT INTO `system_menu` VALUES (9, 2, '权限设置', '/system/auth/insert,/system/auth', 1, 'auth', 'layui-icon-auz', 5, 0, 1, 'admin', '2019-01-25 15:45:54');
INSERT INTO `system_menu` VALUES (10, 3, '菜单列表', '/system/menu/list/query', 2, '', 'layui-icon-align-left', 1, 0, 1, 'admin', '2019-02-13 17:47:03');
INSERT INTO `system_menu` VALUES (11, 3, '添加菜单', '/system/menu/insert,/system/menu/add', 1, 'add', 'layui-icon-add-1', 2, 0, 1, 'admin', '2019-01-25 18:02:19');
INSERT INTO `system_menu` VALUES (12, 3, '修改菜单', '/system/menu/update,/system/menu/edit', 1, 'edit', 'layui-icon-edit', 3, 0, 1, 'admin', '2019-01-25 18:04:27');
INSERT INTO `system_menu` VALUES (13, 3, '删除菜单', '/system/menu/delete', 1, 'delete', 'layui-icon-delete', 4, 0, 1, 'admin', '2019-01-25 18:05:53');
INSERT INTO `system_menu` VALUES (14, 4, '角色列表', '/system/role/list/query', 2, '', 'layui-icon-align-left', 1, 0, 1, 'admin', '2019-02-13 17:47:39');
INSERT INTO `system_menu` VALUES (15, 4, '添加角色', '/system/role/insert,/system/role/add', 1, 'add', 'layui-icon-add-1', 2, 0, 1, 'admin', '2019-01-25 18:12:48');
INSERT INTO `system_menu` VALUES (16, 4, '修改角色', '/system/role/update,/system/role/edit', 1, 'edit', 'layui-icon-edit', 3, 0, 1, 'admin', '2019-01-25 18:14:58');
INSERT INTO `system_menu` VALUES (17, 0, '系统管理', '', 0, '', 'layui-icon-set', 33, 0, 1, 'admin', '2019-01-25 18:07:57');
INSERT INTO `system_menu` VALUES (18, 17, '系统配置', '/system/config/website', 0, '', 'layui-icon-component', 1, 0, 1, 'admin', '2019-01-25 18:09:06');
INSERT INTO `system_menu` VALUES (19, 17, '邮箱配置', '/system/config/email', 0, '', 'layui-icon-template', 2, 0, 1, 'admin', '2019-01-25 18:09:51');
INSERT INTO `system_menu` VALUES (20, 17, '操作日志', '/system/operation/list', 0, '', 'layui-icon-align-left', 3, 0, 1, 'admin', '2019-02-13 12:29:54');
INSERT INTO `system_menu` VALUES (21, 18, '修改系统配置', '/system/config/website/update', 1, 'edit', 'layui-icon-edit', 1, 0, 1, 'admin', '2019-01-25 18:10:48');
INSERT INTO `system_menu` VALUES (22, 19, '修改邮箱配置', '/system/config/email/update', 1, 'edit', 'layui-icon-edit', 1, 0, 1, 'admin', '2019-01-25 18:11:18');
INSERT INTO `system_menu` VALUES (23, 20, '操作日志列表', '/system/operation/list/query', 2, '', 'layui-icon-align-left', 1, 0, 1, 'admin', '2019-02-13 17:49:59');
INSERT INTO `system_menu` VALUES (24, 0, '任务调度', '', 0, '', 'layui-icon-notice', 10, 0, 1, 'admin', '2020-01-04 16:48:51');
INSERT INTO `system_menu` VALUES (25, 31, '线程列表', '/system/thread/list/query', 2, '', 'layui-icon-align-left', 0, 0, 1, 'admin', '2020-01-04 16:49:56');
INSERT INTO `system_menu` VALUES (26, 31, '添加线程', '/system/thread/insert,/system/thread/add', 1, 'add', 'layui-icon-add-1', 1, 0, 1, 'admin', '2020-01-04 16:51:33');
INSERT INTO `system_menu` VALUES (27, 31, '修改线程', '/system/thread/update,/system/thread/edit', 1, 'edit', '', 2, 0, 1, 'admin', '2020-01-04 16:52:31');
INSERT INTO `system_menu` VALUES (28, 31, '删除线程', '/system/thread/delete', 1, 'delete', 'layui-icon-delete', 3, 0, 1, 'admin', '2020-01-04 16:53:43');
INSERT INTO `system_menu` VALUES (29, 31, '开启线程', '/system/thread/start', 1, 'start', 'layui-icon-play', 4, 0, 1, 'admin', '2020-01-07 18:33:59');
INSERT INTO `system_menu` VALUES (30, 31, '关闭线程', '/system/thread/stop', 1, 'stop', 'layui-icon-pause', 5, 0, 1, 'admin', '2020-01-07 18:34:44');
INSERT INTO `system_menu` VALUES (31, 24, '交易线程', '/system/thread/list', 0, '', 'layui-icon-align-left', 0, 0, 1, 'admin', '2020-01-07 18:39:42');
INSERT INTO `system_menu` VALUES (32, 24, '交易所管理', '/system/thread/platform/list', 0, '', 'layui-icon-align-left', 1, 0, 1, 'admin', '2020-01-07 19:31:39');
INSERT INTO `system_menu` VALUES (33, 24, '线程配置', '/system/thread/config/list', 0, '', 'layui-icon-align-left', 2, 0, 1, 'admin', '2020-01-07 19:33:25');
INSERT INTO `system_menu` VALUES (34, 32, '添加交易所', '/system/thread/platform/insert,/system/thread/platform/add', 1, 'add', 'layui-icon-add-1', 0, 0, 1, 'admin', '2020-01-07 19:36:55');
INSERT INTO `system_menu` VALUES (35, 32, '修改交易所', '/system/thread/platform/update,/system/thread/platform/edit', 1, 'edit', 'layui-icon-edit', 1, 0, 1, 'admin', '2020-01-07 19:38:04');
INSERT INTO `system_menu` VALUES (36, 32, '删除交易所', '/system/thread/platform/delete', 1, 'delete', 'layui-icon-delete', 2, 0, 1, 'admin', '2020-01-07 19:39:02');
INSERT INTO `system_menu` VALUES (37, 32, '交易所列表', '/system/thread/platform/list/query', 2, '', '', 3, 0, 1, 'admin', '2020-01-07 19:39:45');
INSERT INTO `system_menu` VALUES (38, 33, '添加线程配置', '/system/thread/config/insert,/system/thread/config/add', 1, 'add', 'layui-icon-add-1', 0, 0, 1, 'admin', '2020-01-07 19:40:56');
INSERT INTO `system_menu` VALUES (39, 33, '修改线程配置', '/system/thread/config/update,/system/thread/config/edit', 1, 'edit', 'layui-icon-edit', 1, 0, 1, 'admin', '2020-01-07 19:42:07');
INSERT INTO `system_menu` VALUES (40, 33, '删除线程配置', '/system/thread/config/delete', 1, 'delete', 'layui-icon-delete', 2, 0, 1, 'admin', '2020-01-07 19:43:02');
INSERT INTO `system_menu` VALUES (41, 33, '线程配置列表', '/system/thread/config/list/query', 2, '', '', 3, 0, 1, 'admin', '2020-01-07 19:43:49');
INSERT INTO `system_menu` VALUES (42, 31, '一键开启', '/system/thread/oneKeyStart', 1, 'oneKeyStart', 'layui-icon-play', 6, 0, 1, 'admin', '2020-01-15 10:56:42');
INSERT INTO `system_menu` VALUES (43, 31, '一键关闭', '/system/thread/oneKeyStop', 1, 'oneKeyStop', 'layui-icon-pause', 7, 0, 1, 'admin', '2020-01-15 10:57:30');
INSERT INTO `system_menu` VALUES (44, 31, '线程详情', '/system/thread/detail', 1, 'detail', '', 7, 0, 1, 'admin', '2020-01-15 15:36:20');

-- ----------------------------
-- Table structure for system_operation
-- ----------------------------
DROP TABLE IF EXISTS `system_operation`;
CREATE TABLE `system_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作名称',
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求链接',
  `params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '请求参数',
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备标示',
  `admin_id` int(11) NOT NULL DEFAULT 0 COMMENT '操作人',
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `create_idate` int(10) NULL DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 198 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_operation
-- ----------------------------
INSERT INTO `system_operation` VALUES (1, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"a324\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 10:46:52', 20191108);
INSERT INTO `system_operation` VALUES (2, '更新网站设置', '/system/config/website/update', '{\"value\":\"{\\\"name\\\":\\\"后台管理系统\\\",\\\"gateway\\\":\\\"#\\\",\\\"customer\\\":\\\"个体\\\",\\\"author\\\":\\\"图灵技术团队\\\",\\\"mobile\\\":\\\"18897956300\\\",\\\"copyright\\\":\\\"© 2019 <a href=\\\\\\\"#\\\\\\\">图灵科技有限公司</a> 版权所有\\\"}\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 10:53:08', 20191108);
INSERT INTO `system_operation` VALUES (3, '更新邮箱配置', '/system/config/email/update', '{\"value\":\"{\\\"host_name\\\":\\\"smtp.139.com\\\",\\\"port\\\":\\\"465\\\",\\\"user_name\\\":\\\"oliwens@163.com\\\",\\\"name\\\":\\\"图灵科技有限公司\\\",\\\"password\\\":\\\"aoliwen@163.com\\\"}\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 10:56:07', 20191108);
INSERT INTO `system_operation` VALUES (4, '更新系统用户', '/system/user/update', '{\"file\":\"\",\"nickName\":\"敖利文\",\"sex\":\"1\",\"mobile\":\"18000716092\",\"remark\":\"哈哈哈\",\"avatar\":\"admin-demo/2a8fb3f0093548218cdbe8ec020f7ef7.png\",\"userName\":\"lihaitao\",\"userId\":\"2\",\"email\":\"178263682@qq.com\",\"status\":\"0\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 10:56:55', 20191108);
INSERT INTO `system_operation` VALUES (5, '更新系统用户', '/system/user/update', '{\"file\":\"\",\"nickName\":\"敖利文\",\"sex\":\"1\",\"mobile\":\"18897956300\",\"remark\":\"哈哈哈\",\"avatar\":\"admin-demo/2a8fb3f0093548218cdbe8ec020f7ef7.png\",\"userName\":\"lihaitao\",\"userId\":\"2\",\"email\":\"178263682@qq.com\",\"status\":\"0\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 10:57:08', 20191108);
INSERT INTO `system_operation` VALUES (6, '更新网站设置', '/system/config/website/update', '{\"value\":\"{\\\"name\\\":\\\"后台管理系统\\\",\\\"gateway\\\":\\\"#\\\",\\\"customer\\\":\\\"图灵科技有限公司\\\",\\\"author\\\":\\\"图灵技术团队\\\",\\\"mobile\\\":\\\"18897956300\\\",\\\"copyright\\\":\\\"© 2019 <a href=\\\\\\\"#\\\\\\\">图灵科技有限公司</a> 版权所有\\\"}\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 10:59:49', 20191108);
INSERT INTO `system_operation` VALUES (7, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 11:27:27', 20191108);
INSERT INTO `system_operation` VALUES (8, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"ZGWN\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 0, NULL, '2019-11-08 11:29:01', 20191108);
INSERT INTO `system_operation` VALUES (9, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"PCG9\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 0, NULL, '2019-11-08 11:29:16', 20191108);
INSERT INTO `system_operation` VALUES (10, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"2KYA\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 0, NULL, '2019-11-08 11:29:23', 20191108);
INSERT INTO `system_operation` VALUES (11, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"JERR\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 0, NULL, '2019-11-08 11:29:30', 20191108);
INSERT INTO `system_operation` VALUES (12, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"5pvd\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-08 11:29:54', 20191108);
INSERT INTO `system_operation` VALUES (13, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 0, NULL, '2019-11-08 12:09:44', 20191108);
INSERT INTO `system_operation` VALUES (14, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"yc57\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36', 1, 'admin', '2019-11-09 11:26:38', 20191109);
INSERT INTO `system_operation` VALUES (15, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"a2bp\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36', 1, 'admin', '2019-12-21 14:09:12', 20191221);
INSERT INTO `system_operation` VALUES (16, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"73et\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36', 1, 'admin', '2019-12-21 15:31:03', 20191221);
INSERT INTO `system_operation` VALUES (17, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"xqt4\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-03 13:18:30', 20200103);
INSERT INTO `system_operation` VALUES (18, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"yjcx\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-04 16:46:21', 20200104);
INSERT INTO `system_operation` VALUES (19, '添加菜单', '/system/menu/insert', '{\"name\":\"任务调度\",\"icon\":\"layui-icon-notice\",\"pid\":\"17\",\"tag\":\"\",\"sort\":\"3\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-04 16:48:51', 20200104);
INSERT INTO `system_operation` VALUES (20, '添加菜单', '/system/menu/insert', '{\"name\":\"线程列表\",\"icon\":\"layui-icon-align-left\",\"pid\":\"24\",\"tag\":\"\",\"sort\":\"0\",\"type\":\"2\",\"url\":\"/system/thread/list/query\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-04 16:49:56', 20200104);
INSERT INTO `system_operation` VALUES (21, '添加菜单', '/system/menu/insert', '{\"name\":\"添加线程\",\"icon\":\"layui-icon-add-1\",\"pid\":\"24\",\"tag\":\"add\",\"sort\":\"1\",\"type\":\"1\",\"url\":\"/system/thread/insert,/system/thread/add\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-04 16:51:33', 20200104);
INSERT INTO `system_operation` VALUES (22, '添加菜单', '/system/menu/insert', '{\"name\":\"修改线程\",\"icon\":\"\",\"pid\":\"24\",\"tag\":\"edit\",\"sort\":\"2\",\"type\":\"1\",\"url\":\"/system/thread/update,/system/thread/edit\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-04 16:52:31', 20200104);
INSERT INTO `system_operation` VALUES (23, '添加菜单', '/system/menu/insert', '{\"name\":\"删除线程\",\"icon\":\"layui-icon-delete\",\"pid\":\"24\",\"tag\":\"delete\",\"sort\":\"3\",\"type\":\"1\",\"url\":\"/system/thread/delete\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-04 16:53:43', 20200104);
INSERT INTO `system_operation` VALUES (24, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"tbqu\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:11:42', 20200107);
INSERT INTO `system_operation` VALUES (25, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"2beg\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:16:19', 20200107);
INSERT INTO `system_operation` VALUES (26, '登录', '/system/login/check', '{\"password\":\"123qweased\",\"code\":\"twmq\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 18:22:50', 20200107);
INSERT INTO `system_operation` VALUES (27, '登录', '/system/login/check', '{\"password\":\"123qweased\",\"code\":\"fjnn\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 18:22:57', 20200107);
INSERT INTO `system_operation` VALUES (28, '登录', '/system/login/check', '{\"password\":\"123qweqasd\",\"code\":\"ua6h\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 18:23:11', 20200107);
INSERT INTO `system_operation` VALUES (29, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"yxjw\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:23:22', 20200107);
INSERT INTO `system_operation` VALUES (30, '更新菜单', '/system/menu/update', '{\"name\":\"任务调度\",\"icon\":\"layui-icon-notice\",\"pid\":\"0\",\"id\":\"24\",\"tag\":\"\",\"sort\":\"3\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:23:55', 20200107);
INSERT INTO `system_operation` VALUES (31, '更新菜单', '/system/menu/update', '{\"name\":\"任务调度\",\"icon\":\"layui-icon-notice\",\"pid\":\"17\",\"id\":\"24\",\"tag\":\"\",\"sort\":\"3\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:24:23', 20200107);
INSERT INTO `system_operation` VALUES (32, '添加菜单', '/system/menu/insert', '{\"name\":\"开启线程\",\"icon\":\"layui-icon-play\",\"pid\":\"24\",\"tag\":\"start\",\"sort\":\"4\",\"type\":\"1\",\"url\":\"/system/threas/start\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:33:59', 20200107);
INSERT INTO `system_operation` VALUES (33, '添加菜单', '/system/menu/insert', '{\"name\":\"关闭线程\",\"icon\":\"layui-icon-pause\",\"pid\":\"24\",\"tag\":\"stop\",\"sort\":\"5\",\"type\":\"1\",\"url\":\"/system/thread/stop\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:34:44', 20200107);
INSERT INTO `system_operation` VALUES (34, '更新菜单', '/system/menu/update', '{\"name\":\"任务调度\",\"icon\":\"layui-icon-notice\",\"pid\":\"0\",\"id\":\"24\",\"tag\":\"\",\"sort\":\"3\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:37:59', 20200107);
INSERT INTO `system_operation` VALUES (35, '添加菜单', '/system/menu/insert', '{\"name\":\"交易线程\",\"icon\":\"\",\"pid\":\"24\",\"tag\":\"\",\"sort\":\"0\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:39:42', 20200107);
INSERT INTO `system_operation` VALUES (36, '更新菜单', '/system/menu/update', '{\"name\":\"任务调度\",\"icon\":\"layui-icon-notice\",\"pid\":\"0\",\"id\":\"24\",\"tag\":\"\",\"sort\":\"5\",\"type\":\"0\",\"url\":\"\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 18:39:58', 20200107);
INSERT INTO `system_operation` VALUES (37, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"wnvm\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:27:35', 20200107);
INSERT INTO `system_operation` VALUES (38, '更新菜单', '/system/menu/update', '{\"name\":\"任务调度\",\"icon\":\"layui-icon-notice\",\"pid\":\"0\",\"id\":\"24\",\"tag\":\"\",\"sort\":\"10\",\"type\":\"0\",\"url\":\"\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:27:49', 20200107);
INSERT INTO `system_operation` VALUES (39, '更新菜单', '/system/menu/update', '{\"name\":\"线程列表\",\"icon\":\"layui-icon-align-left\",\"pid\":\"31\",\"id\":\"25\",\"tag\":\"\",\"sort\":\"0\",\"type\":\"2\",\"url\":\"/system/thread/list/query\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:28:16', 20200107);
INSERT INTO `system_operation` VALUES (40, '更新菜单', '/system/menu/update', '{\"name\":\"交易线程\",\"icon\":\"\",\"pid\":\"31\",\"id\":\"31\",\"tag\":\"\",\"sort\":\"0\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:28:21', 20200107);
INSERT INTO `system_operation` VALUES (41, '更新菜单', '/system/menu/update', '{\"name\":\"交易线程\",\"icon\":\"\",\"pid\":\"24\",\"id\":\"31\",\"tag\":\"\",\"sort\":\"0\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:29:04', 20200107);
INSERT INTO `system_operation` VALUES (42, '更新菜单', '/system/menu/update', '{\"name\":\"添加线程\",\"icon\":\"layui-icon-add-1\",\"pid\":\"31\",\"id\":\"26\",\"tag\":\"add\",\"sort\":\"1\",\"type\":\"1\",\"url\":\"/system/thread/insert,/system/thread/add\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:29:20', 20200107);
INSERT INTO `system_operation` VALUES (43, '更新菜单', '/system/menu/update', '{\"name\":\"修改线程\",\"icon\":\"\",\"pid\":\"31\",\"id\":\"27\",\"tag\":\"edit\",\"sort\":\"2\",\"type\":\"1\",\"url\":\"/system/thread/update,/system/thread/edit\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:29:26', 20200107);
INSERT INTO `system_operation` VALUES (44, '更新菜单', '/system/menu/update', '{\"name\":\"删除线程\",\"icon\":\"layui-icon-delete\",\"pid\":\"31\",\"id\":\"28\",\"tag\":\"delete\",\"sort\":\"3\",\"type\":\"1\",\"url\":\"/system/thread/delete\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:29:31', 20200107);
INSERT INTO `system_operation` VALUES (45, '更新菜单', '/system/menu/update', '{\"name\":\"开启线程\",\"icon\":\"layui-icon-play\",\"pid\":\"31\",\"id\":\"29\",\"tag\":\"start\",\"sort\":\"4\",\"type\":\"1\",\"url\":\"/system/threas/start\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:29:36', 20200107);
INSERT INTO `system_operation` VALUES (46, '更新菜单', '/system/menu/update', '{\"name\":\"关闭线程\",\"icon\":\"layui-icon-pause\",\"pid\":\"31\",\"id\":\"30\",\"tag\":\"stop\",\"sort\":\"5\",\"type\":\"1\",\"url\":\"/system/thread/stop\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:29:41', 20200107);
INSERT INTO `system_operation` VALUES (47, '添加菜单', '/system/menu/insert', '{\"name\":\"交易所管理\",\"icon\":\"layui-icon-align-left\",\"pid\":\"24\",\"tag\":\"\",\"sort\":\"1\",\"type\":\"0\",\"url\":\"/system/thread/platform/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:31:39', 20200107);
INSERT INTO `system_operation` VALUES (48, '添加菜单', '/system/menu/insert', '{\"name\":\"线程配置\",\"icon\":\"layui-icon-align-left\",\"pid\":\"24\",\"tag\":\"\",\"sort\":\"2\",\"type\":\"0\",\"url\":\"/system./thread/config/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:33:25', 20200107);
INSERT INTO `system_operation` VALUES (49, '更新菜单', '/system/menu/update', '{\"name\":\"线程配置\",\"icon\":\"layui-icon-align-left\",\"pid\":\"24\",\"id\":\"33\",\"tag\":\"\",\"sort\":\"2\",\"type\":\"0\",\"url\":\"/system/thread/config/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:33:34', 20200107);
INSERT INTO `system_operation` VALUES (50, '更新菜单', '/system/menu/update', '{\"name\":\"交易线程\",\"icon\":\"layui-icon-align-left\",\"pid\":\"24\",\"id\":\"31\",\"tag\":\"\",\"sort\":\"0\",\"type\":\"0\",\"url\":\"/system/thread/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:33:43', 20200107);
INSERT INTO `system_operation` VALUES (51, '添加菜单', '/system/menu/insert', '{\"name\":\"添加交易所\",\"icon\":\"layui-icon-add-1\",\"pid\":\"32\",\"tag\":\"add\",\"sort\":\"0\",\"type\":\"1\",\"url\":\"/system/thread/platform/insert,/system/thread/platform/add\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:36:56', 20200107);
INSERT INTO `system_operation` VALUES (52, '添加菜单', '/system/menu/insert', '{\"name\":\"修改交易所\",\"icon\":\"layui-icon-edit\",\"pid\":\"32\",\"tag\":\"edit\",\"sort\":\"1\",\"type\":\"1\",\"url\":\"/system/thread/platform/update,/system/thread/platform/edit\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:38:04', 20200107);
INSERT INTO `system_operation` VALUES (53, '添加菜单', '/system/menu/insert', '{\"name\":\"删除交易所\",\"icon\":\"layui-icon-delete\",\"pid\":\"32\",\"tag\":\"delete\",\"sort\":\"2\",\"type\":\"1\",\"url\":\"/system/thread/platform/delete\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:39:02', 20200107);
INSERT INTO `system_operation` VALUES (54, '添加菜单', '/system/menu/insert', '{\"name\":\"交易所列表\",\"icon\":\"\",\"pid\":\"32\",\"tag\":\"\",\"sort\":\"3\",\"type\":\"2\",\"url\":\"/system/thread/platform/list/query\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:39:45', 20200107);
INSERT INTO `system_operation` VALUES (55, '添加菜单', '/system/menu/insert', '{\"name\":\"添加线程配置\",\"icon\":\"layui-icon-add-1\",\"pid\":\"33\",\"tag\":\"add\",\"sort\":\"0\",\"type\":\"1\",\"url\":\"/system/thread/config/insert,/system/thread/config/add\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:40:56', 20200107);
INSERT INTO `system_operation` VALUES (56, '添加菜单', '/system/menu/insert', '{\"name\":\"修改线程配置\",\"icon\":\"layui-icon-edit\",\"pid\":\"33\",\"tag\":\"edit\",\"sort\":\"2\",\"type\":\"1\",\"url\":\"/system/thread/config/update,/system/thread/config/edit\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:42:07', 20200107);
INSERT INTO `system_operation` VALUES (57, '添加菜单', '/system/menu/insert', '{\"name\":\"删除线程配置\",\"icon\":\"layui-icon-delete\",\"pid\":\"33\",\"tag\":\"delete\",\"sort\":\"2\",\"type\":\"1\",\"url\":\"/system/thread/config/delete\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:43:02', 20200107);
INSERT INTO `system_operation` VALUES (58, '更新菜单', '/system/menu/update', '{\"name\":\"修改线程配置\",\"icon\":\"layui-icon-edit\",\"pid\":\"33\",\"id\":\"39\",\"tag\":\"edit\",\"sort\":\"1\",\"type\":\"1\",\"url\":\"/system/thread/config/update,/system/thread/config/edit\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:43:09', 20200107);
INSERT INTO `system_operation` VALUES (59, '添加菜单', '/system/menu/insert', '{\"name\":\"线程配置列表\",\"icon\":\"\",\"pid\":\"33\",\"tag\":\"\",\"sort\":\"3\",\"type\":\"2\",\"url\":\"/system/thread/config/list/query\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:43:49', 20200107);
INSERT INTO `system_operation` VALUES (60, '更新菜单', '/system/menu/update', '{\"name\":\"线程配置\",\"icon\":\"layui-icon-align-left\",\"pid\":\"24\",\"id\":\"33\",\"tag\":\"\",\"sort\":\"2\",\"type\":\"0\",\"url\":\"/system/thread/config/list\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:48:52', 20200107);
INSERT INTO `system_operation` VALUES (61, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:53:01', 20200107);
INSERT INTO `system_operation` VALUES (62, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"2frp\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 19:53:13', 20200107);
INSERT INTO `system_operation` VALUES (63, '更新角色', '/system/role/update', '{\"roleId\":\"3\",\"menuIds\":\"24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,1,3,10,11,12,13,17,18,21,19,22,20,23\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:04:23', 20200107);
INSERT INTO `system_operation` VALUES (64, '更新角色', '/system/role/update', '{\"roleId\":\"3\",\"menuIds\":\"24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,1,2,5,6,7,8,9,3,10,11,12,13,4,14,15,16,17,18,21,19,22,20,23\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:04:28', 20200107);
INSERT INTO `system_operation` VALUES (65, '更新角色', '/system/role/update', '{\"roleId\":\"2\",\"menuIds\":\"24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,1,2,5,6,7,8,9,3,10,11,12,13,4,14,15,16\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:04:43', 20200107);
INSERT INTO `system_operation` VALUES (66, '更新角色', '/system/role/update', '{\"roleId\":\"3\",\"menuIds\":\"24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,1,2,5,6,7,8,9,3,10,11,12,13,4,14,15,16\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:04:54', 20200107);
INSERT INTO `system_operation` VALUES (67, '更新角色', '/system/role/update', '{\"roleId\":\"3\",\"menuIds\":\"24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,1,3,10,11,12,13,4,14,15,16\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:05:02', 20200107);
INSERT INTO `system_operation` VALUES (68, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"stt4\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 20:06:35', 20200107);
INSERT INTO `system_operation` VALUES (69, '更新角色', '/system/role/update', '{\"roleId\":\"3\",\"menuIds\":\"24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,1,2,5,6,7,8,9,3,10,11,12,13,4,14,15,16\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:09:18', 20200107);
INSERT INTO `system_operation` VALUES (70, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:15:23', 20200107);
INSERT INTO `system_operation` VALUES (71, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"mesd\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 20:15:33', 20200107);
INSERT INTO `system_operation` VALUES (72, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"xa2d\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 20:15:46', 20200107);
INSERT INTO `system_operation` VALUES (73, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"ref7\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 20:16:08', 20200107);
INSERT INTO `system_operation` VALUES (74, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"hyfm\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:16:32', 20200107);
INSERT INTO `system_operation` VALUES (75, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"4aar\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:50:28', 20200107);
INSERT INTO `system_operation` VALUES (76, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"fznp\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-07 20:58:02', 20200107);
INSERT INTO `system_operation` VALUES (77, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"hkqy\",\"userName\":\"admin\"}', '192.168.81.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko', 0, NULL, '2020-01-07 21:01:22', 20200107);
INSERT INTO `system_operation` VALUES (78, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"udqp\",\"userName\":\"admin\"}', '192.168.81.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko', 1, 'admin', '2020-01-07 21:01:30', 20200107);
INSERT INTO `system_operation` VALUES (79, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"kgdv\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-07 21:07:57', 20200107);
INSERT INTO `system_operation` VALUES (80, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"e8kc\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 3, 'zhangliu', '2020-01-07 21:08:10', 20200107);
INSERT INTO `system_operation` VALUES (81, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"nfrq\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 11:52:58', 20200108);
INSERT INTO `system_operation` VALUES (82, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"9yxg\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 11:53:05', 20200108);
INSERT INTO `system_operation` VALUES (83, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"s3sr\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 11:55:57', 20200108);
INSERT INTO `system_operation` VALUES (84, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"ytsz\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 12:01:32', 20200108);
INSERT INTO `system_operation` VALUES (85, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 12:02:38', 20200108);
INSERT INTO `system_operation` VALUES (86, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"4awx\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 12:02:54', 20200108);
INSERT INTO `system_operation` VALUES (87, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"hu82\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 12:05:53', 20200108);
INSERT INTO `system_operation` VALUES (88, '登录', '/system/login/check', '{\"password\":\"123456789\",\"code\":\"kvdc\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:16:47', 20200108);
INSERT INTO `system_operation` VALUES (89, '登录', '/system/login/check', '{\"password\":\"123456789\",\"code\":\"usdw\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:17:11', 20200108);
INSERT INTO `system_operation` VALUES (90, '登录', '/system/login/check', '{\"password\":\"aoliwen\",\"code\":\"hstv\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:17:25', 20200108);
INSERT INTO `system_operation` VALUES (91, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"ybw8\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:17:42', 20200108);
INSERT INTO `system_operation` VALUES (92, '登录', '/system/login/check', '{\"password\":\"admin\",\"code\":\"7tgc\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:18:00', 20200108);
INSERT INTO `system_operation` VALUES (93, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"pjm3\",\"userName\":\"oliwen\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:18:34', 20200108);
INSERT INTO `system_operation` VALUES (94, '登录', '/system/login/check', '{\"password\":\"12456\",\"code\":\"uavb\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:21:38', 20200108);
INSERT INTO `system_operation` VALUES (95, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"ykk2\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:21:54', 20200108);
INSERT INTO `system_operation` VALUES (96, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"2mqr\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-08 14:22:07', 20200108);
INSERT INTO `system_operation` VALUES (97, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"ptvn\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 3, 'zhangliu', '2020-01-08 14:22:16', 20200108);
INSERT INTO `system_operation` VALUES (98, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"mfke\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 14:26:39', 20200108);
INSERT INTO `system_operation` VALUES (99, '更新系统用户状态', '/system/user/update/status', '{\"userId\":\"2\",\"status\":\"2\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 14:26:47', 20200108);
INSERT INTO `system_operation` VALUES (100, '更新角色', '/system/role/update', '{\"roleId\":\"3\",\"menuIds\":\"24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,17,18,21,19,22,20,23\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 14:27:51', 20200108);
INSERT INTO `system_operation` VALUES (101, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"axqb\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 15:30:30', 20200108);
INSERT INTO `system_operation` VALUES (102, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"wezs\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 18:00:42', 20200108);
INSERT INTO `system_operation` VALUES (103, '更新菜单', '/system/menu/update', '{\"name\":\"开启线程\",\"icon\":\"layui-icon-play\",\"pid\":\"31\",\"id\":\"29\",\"tag\":\"start\",\"sort\":\"4\",\"type\":\"1\",\"url\":\"/system/thread/start\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-08 18:07:37', 20200108);
INSERT INTO `system_operation` VALUES (104, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"srua\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-09 12:33:00', 20200109);
INSERT INTO `system_operation` VALUES (105, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"PT6N\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-09 14:31:14', 20200109);
INSERT INTO `system_operation` VALUES (106, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"HVZU\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-09 14:31:19', 20200109);
INSERT INTO `system_operation` VALUES (107, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"N9HK\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-09 14:31:28', 20200109);
INSERT INTO `system_operation` VALUES (108, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"HEZN\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 0, NULL, '2020-01-09 14:31:39', 20200109);
INSERT INTO `system_operation` VALUES (109, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"vzds\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-09 14:32:09', 20200109);
INSERT INTO `system_operation` VALUES (110, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"vzbc\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-09 14:42:42', 20200109);
INSERT INTO `system_operation` VALUES (111, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"xxzu\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-09 17:56:30', 20200109);
INSERT INTO `system_operation` VALUES (112, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"xnjx\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 1, 'admin', '2020-01-09 18:54:46', 20200109);
INSERT INTO `system_operation` VALUES (113, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"edgd\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-13 10:54:23', 20200113);
INSERT INTO `system_operation` VALUES (114, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"5vge\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-13 11:51:57', 20200113);
INSERT INTO `system_operation` VALUES (115, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"z3mn\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-13 14:10:19', 20200113);
INSERT INTO `system_operation` VALUES (116, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"7nfw\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-13 14:10:23', 20200113);
INSERT INTO `system_operation` VALUES (117, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"dpgk\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-13 14:26:03', 20200113);
INSERT INTO `system_operation` VALUES (118, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"r8sg\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-13 15:53:30', 20200113);
INSERT INTO `system_operation` VALUES (119, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"r8sg\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-13 15:53:30', 20200113);
INSERT INTO `system_operation` VALUES (120, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"r8sg\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-13 15:53:33', 20200113);
INSERT INTO `system_operation` VALUES (121, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"TDVB\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-14 14:52:02', 20200114);
INSERT INTO `system_operation` VALUES (122, '登录', '/system/login/check', '{\"password\":\"123QWEASD\",\"code\":\"E84M\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-14 14:52:10', 20200114);
INSERT INTO `system_operation` VALUES (123, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"snhr\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-14 14:52:35', 20200114);
INSERT INTO `system_operation` VALUES (124, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"wmh2\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-14 16:19:04', 20200114);
INSERT INTO `system_operation` VALUES (125, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"gsda\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 10:49:13', 20200115);
INSERT INTO `system_operation` VALUES (126, '添加菜单', '/system/menu/insert', '{\"name\":\"一键开启\",\"icon\":\"\",\"pid\":\"31\",\"tag\":\"\",\"sort\":\"4\",\"type\":\"2\",\"url\":\"/system/thread/onekey/start\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 10:56:42', 20200115);
INSERT INTO `system_operation` VALUES (127, '添加菜单', '/system/menu/insert', '{\"name\":\"一键关闭\",\"icon\":\"\",\"pid\":\"31\",\"tag\":\"\",\"sort\":\"5\",\"type\":\"2\",\"url\":\"/system/thread/onekey/stop\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 10:57:30', 20200115);
INSERT INTO `system_operation` VALUES (128, '更新菜单', '/system/menu/update', '{\"name\":\"一键开启\",\"icon\":\"layui-icon-play\",\"pid\":\"31\",\"id\":\"42\",\"tag\":\"oneKeystart\",\"sort\":\"4\",\"type\":\"1\",\"url\":\"/system/thread/oneKeystart\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 11:03:57', 20200115);
INSERT INTO `system_operation` VALUES (129, '更新菜单', '/system/menu/update', '{\"name\":\"一键关闭\",\"icon\":\"layui-icon-pause\",\"pid\":\"31\",\"id\":\"43\",\"tag\":\"stop\",\"sort\":\"5\",\"type\":\"1\",\"url\":\"/system/thread/oneKey/stop\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 11:04:25', 20200115);
INSERT INTO `system_operation` VALUES (130, '更新菜单', '/system/menu/update', '{\"name\":\"一键关闭\",\"icon\":\"layui-icon-pause\",\"pid\":\"31\",\"id\":\"43\",\"tag\":\"oneKeystop\",\"sort\":\"5\",\"type\":\"1\",\"url\":\"/system/thread/oneKeystop\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 11:04:39', 20200115);
INSERT INTO `system_operation` VALUES (131, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 11:07:50', 20200115);
INSERT INTO `system_operation` VALUES (132, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"fyc6\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 11:08:04', 20200115);
INSERT INTO `system_operation` VALUES (133, '更新菜单', '/system/menu/update', '{\"name\":\"一键开启\",\"icon\":\"layui-icon-play\",\"pid\":\"31\",\"id\":\"42\",\"tag\":\"oneKeyStart\",\"sort\":\"6\",\"type\":\"1\",\"url\":\"/system/thread/oneKeyStart\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 11:12:09', 20200115);
INSERT INTO `system_operation` VALUES (134, '更新菜单', '/system/menu/update', '{\"name\":\"一键关闭\",\"icon\":\"layui-icon-pause\",\"pid\":\"31\",\"id\":\"43\",\"tag\":\"oneKeyStop\",\"sort\":\"7\",\"type\":\"1\",\"url\":\"/system/thread/oneKeyStop\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 11:12:23', 20200115);
INSERT INTO `system_operation` VALUES (135, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"uacy\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 12:33:15', 20200115);
INSERT INTO `system_operation` VALUES (136, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"hu3y\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 13:55:20', 20200115);
INSERT INTO `system_operation` VALUES (137, '添加菜单', '/system/menu/insert', '{\"name\":\"线程详情\",\"icon\":\"\",\"pid\":\"31\",\"tag\":\"detail\",\"sort\":\"7\",\"type\":\"1\",\"url\":\"/system/thread/detail\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 15:36:20', 20200115);
INSERT INTO `system_operation` VALUES (138, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"gxrj\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 16:13:44', 20200115);
INSERT INTO `system_operation` VALUES (139, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"nbwa\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 17:26:55', 20200115);
INSERT INTO `system_operation` VALUES (140, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"bruq\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 17:58:50', 20200115);
INSERT INTO `system_operation` VALUES (141, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 17:59:04', 20200115);
INSERT INTO `system_operation` VALUES (142, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"nvg5\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-15 17:59:25', 20200115);
INSERT INTO `system_operation` VALUES (143, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"82jd\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 17:59:32', 20200115);
INSERT INTO `system_operation` VALUES (144, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"xtbu\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-15 18:00:27', 20200115);
INSERT INTO `system_operation` VALUES (145, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"42qf\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 18:00:33', 20200115);
INSERT INTO `system_operation` VALUES (146, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 18:01:43', 20200115);
INSERT INTO `system_operation` VALUES (147, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"3cqa\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-15 18:01:55', 20200115);
INSERT INTO `system_operation` VALUES (148, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"c4ws\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-16 18:01:50', 20200116);
INSERT INTO `system_operation` VALUES (149, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"p72a\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-16 18:06:52', 20200116);
INSERT INTO `system_operation` VALUES (150, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"jnzt\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36', 1, 'admin', '2020-01-16 18:57:30', 20200116);
INSERT INTO `system_operation` VALUES (151, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"v7pt\",\"userName\":\" admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-16 19:09:18', 20200116);
INSERT INTO `system_operation` VALUES (152, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"qkkw\",\"userName\":\" admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-16 19:09:23', 20200116);
INSERT INTO `system_operation` VALUES (153, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"8grs\",\"userName\":\" admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-16 19:09:31', 20200116);
INSERT INTO `system_operation` VALUES (154, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"kjrp\",\"userName\":\" admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-16 19:09:45', 20200116);
INSERT INTO `system_operation` VALUES (155, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"mqyh\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-16 19:10:04', 20200116);
INSERT INTO `system_operation` VALUES (156, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"qgxx\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-16 19:40:19', 20200116);
INSERT INTO `system_operation` VALUES (157, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"2uau\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-16 20:47:39', 20200116);
INSERT INTO `system_operation` VALUES (158, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"yrww\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 11:03:47', 20200117);
INSERT INTO `system_operation` VALUES (159, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"gmtc\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 11:16:03', 20200117);
INSERT INTO `system_operation` VALUES (160, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"hs5x\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 11:42:15', 20200117);
INSERT INTO `system_operation` VALUES (161, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"yrkq\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-17 13:18:48', 20200117);
INSERT INTO `system_operation` VALUES (162, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"ccmk\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-17 13:18:52', 20200117);
INSERT INTO `system_operation` VALUES (163, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"eu4j\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 13:18:56', 20200117);
INSERT INTO `system_operation` VALUES (164, '登录', '/system/login/check', '{\"password\":\"123qweasd`\",\"code\":\"kttv\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-17 15:42:34', 20200117);
INSERT INTO `system_operation` VALUES (165, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"tfax\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 15:42:43', 20200117);
INSERT INTO `system_operation` VALUES (166, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"cgcd\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 16:15:00', 20200117);
INSERT INTO `system_operation` VALUES (167, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"puqr\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-17 16:26:21', 20200117);
INSERT INTO `system_operation` VALUES (168, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"dyse\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 16:26:26', 20200117);
INSERT INTO `system_operation` VALUES (169, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"j2qe\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-17 16:31:39', 20200117);
INSERT INTO `system_operation` VALUES (170, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"htnb\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36', 1, 'admin', '2020-01-18 16:04:05', 20200118);
INSERT INTO `system_operation` VALUES (171, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"eg56\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36', 1, 'admin', '2020-01-20 10:43:58', 20200120);
INSERT INTO `system_operation` VALUES (172, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"vthh\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-20 11:01:09', 20200120);
INSERT INTO `system_operation` VALUES (173, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"wqfa\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-01-20 11:01:14', 20200120);
INSERT INTO `system_operation` VALUES (174, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"reb4\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-01-20 11:01:24', 20200120);
INSERT INTO `system_operation` VALUES (175, '登录', '/system/login/check', '{\"password\":\"123456\",\"code\":\"rqwq\",\"userName\":\"zhangliu\"}', '192.168.50.22', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36', 3, 'zhangliu', '2020-01-20 11:05:52', 20200120);
INSERT INTO `system_operation` VALUES (176, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"sbaa\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36', 1, 'admin', '2020-02-03 14:08:45', 20200203);
INSERT INTO `system_operation` VALUES (177, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"seqq\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 0, NULL, '2020-02-03 15:03:35', 20200203);
INSERT INTO `system_operation` VALUES (178, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"tsqn\",\"userName\":\"admin\"}', '192.168.50.21', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3970.5 Safari/537.36', 1, 'admin', '2020-02-03 15:03:57', 20200203);
INSERT INTO `system_operation` VALUES (179, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"j近期4\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 0, NULL, '2020-03-03 16:00:18', 20200303);
INSERT INTO `system_operation` VALUES (180, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"j近期4\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 0, NULL, '2020-03-03 16:00:18', 20200303);
INSERT INTO `system_operation` VALUES (181, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"y6wq\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-03 16:00:38', 20200303);
INSERT INTO `system_operation` VALUES (182, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"y6wq\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-03 16:00:38', 20200303);
INSERT INTO `system_operation` VALUES (183, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"99wz\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-12 10:47:30', 20200312);
INSERT INTO `system_operation` VALUES (184, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"ames\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-14 13:35:12', 20200314);
INSERT INTO `system_operation` VALUES (185, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"aftr\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-17 13:44:16', 20200317);
INSERT INTO `system_operation` VALUES (186, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"syyr\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-23 14:22:55', 20200323);
INSERT INTO `system_operation` VALUES (187, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"dvmh\",\"userName\":\"admin\"}', '192.168.50.8', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36', 1, 'admin', '2020-03-23 15:31:06', 20200323);
INSERT INTO `system_operation` VALUES (188, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"qydd\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-23 16:57:55', 20200323);
INSERT INTO `system_operation` VALUES (189, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"m5jf\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-23 17:01:25', 20200323);
INSERT INTO `system_operation` VALUES (190, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"nsan\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-25 17:09:26', 20200325);
INSERT INTO `system_operation` VALUES (191, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"rtsm\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36', 1, 'admin', '2020-03-25 17:28:45', 20200325);
INSERT INTO `system_operation` VALUES (192, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"myzz\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36', 1, 'admin', '2020-04-27 17:37:15', 20200427);
INSERT INTO `system_operation` VALUES (193, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36', 1, 'admin', '2020-04-27 17:38:49', 20200427);
INSERT INTO `system_operation` VALUES (194, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"bcvh\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36', 0, NULL, '2020-04-27 17:44:50', 20200427);
INSERT INTO `system_operation` VALUES (195, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"bfeg\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36', 1, 'admin', '2020-04-27 17:44:58', 20200427);
INSERT INTO `system_operation` VALUES (196, '退出', '/system/login/out', '{}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36', 1, 'admin', '2020-04-27 17:51:51', 20200427);
INSERT INTO `system_operation` VALUES (197, '登录', '/system/login/check', '{\"password\":\"123qweasd\",\"code\":\"qsux\",\"userName\":\"admin\"}', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36', 1, 'admin', '2020-04-27 17:52:01', 20200427);

-- ----------------------------
-- Table structure for system_quartz
-- ----------------------------
DROP TABLE IF EXISTS `system_quartz`;
CREATE TABLE `system_quartz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务名称',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '任务所属的分组',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务描述',
  `class_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务的全限定类名',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任务调度周期（cron表达式）',
  `method_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对应要执行的方法名',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0、默认(可用)，1、禁用，2、删除',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'quartz定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_quartz_group
-- ----------------------------
DROP TABLE IF EXISTS `system_quartz_group`;
CREATE TABLE `system_quartz_group`  (
  `id` int(11) NOT NULL,
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分组名称',
  `group_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分组code',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '自定义参数',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0、默认(可用)，1、禁用，2、删除',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父级角色id',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `menu_ids` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限ID列表',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_status` int(1) NOT NULL DEFAULT 0 COMMENT '状态：0正常，1删除',
  `admin_id` int(11) NOT NULL DEFAULT 0,
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `IDX_role_status`(`role_status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, 0, '超级管理员', '1,2,5,6,7,8,9,3,10,11,12,13,4,14,15,16,17,18,21,19,22,20,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44', '拥有至高无上的权利', 0, 1, 'admin', '2018-01-22 00:00:00');
INSERT INTO `system_role` VALUES (2, 0, '普通管理员', '24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,1,2,5,6,7,8,9,3,10,11,12,13,4,14,15,16', '负责简单的系统维护和修改任务，权利一般，任务很重要', 0, 1, 'admin', '2018-01-22 00:00:00');
INSERT INTO `system_role` VALUES (3, 0, '开发人员', '24,31,25,26,27,28,29,30,32,34,35,36,37,33,38,39,40,41,17,18,21,19,22,20,23', '开发项目的人员', 0, 1, 'admin', '2018-01-22 00:00:00');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `sex` int(1) NOT NULL DEFAULT 2,
  `mobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态：0正常，1、禁用，2、删除',
  `admin_id` int(11) NOT NULL DEFAULT 0,
  `admin_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UNIQ_USER_NAME`(`user_name`) USING BTREE,
  INDEX `IDX_USER_NAME`(`user_name`) USING BTREE,
  INDEX `IDX_PASSWORD`(`password`) USING BTREE,
  INDEX `IDX_status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, 'admin', '396eb67616726da735c8c0937d0a5bc38c50f1a9', '超级管理员', 'admin-demo/ab406ad81bda423cae9a4285ccdeade1.png', 1, '15990029693', '1083573600@qq.com', '超级管理员，拥有所有的权限', 0, 1, 'admin', '2018-01-22 00:00:00');
INSERT INTO `system_user` VALUES (2, 'oliwen', 'ddd12ea26087381be12b76e1d9b60fc8c53fbd1e', '敖利文', 'admin-demo/2a8fb3f0093548218cdbe8ec020f7ef7.png', 1, '18897956300', '178263682@qq.com', '哈哈哈', 2, 1, 'admin', '2018-01-22 00:00:00');
INSERT INTO `system_user` VALUES (3, 'zhangliu', 'ddd12ea26087381be12b76e1d9b60fc8c53fbd1e', '张柳', 'admin-demo/2a8fb3f0093548218cdbe8ec020f7ef7.png', 1, '18720034960', '18720034960@163.com', '开发人员', 0, 1, 'admin', '2020-01-07 21:04:45');

SET FOREIGN_KEY_CHECKS = 1;
