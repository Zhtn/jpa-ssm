/*
 Navicat Premium Data Transfer

 Source Server         : zsh
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : jpa_sintro_pms

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 08/07/2021 21:59:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_detail
-- ----------------------------
DROP TABLE IF EXISTS `pms_detail`;
CREATE TABLE `pms_detail`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `detail_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `function` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `render_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `item_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `FK24np4khnqb8sv8ys0omuj7byh`(`item_uuid`) USING BTREE,
  CONSTRAINT `FK24np4khnqb8sv8ys0omuj7byh` FOREIGN KEY (`item_uuid`) REFERENCES `pms_item` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_detail
-- ----------------------------
INSERT INTO `pms_detail` VALUES ('402880e46de153b6016de1541da50000', '2019-10-19 08:04:09', '1', '不能登录', '高', '登录问题1', '张', '0', '2019-11-22 09:15:19', '402880e46ddd6b9c016ddd6bcf280000');
INSERT INTO `pms_detail` VALUES ('402880e46de153b6016de1571ce30003', '2019-10-19 08:07:26', 'xxxxx', '1', '中', '测试', '张', '1', '2019-10-19 08:56:02', '402880e46de153b6016de15602020002');
INSERT INTO `pms_detail` VALUES ('402880e46de1bc10016de1bcb2420000', '2019-10-19 09:58:23', '1', '不能登录', '低', '登录问题111', '张', '1', '2019-10-19 09:58:33', '402880e46ddd6b9c016ddd6bcf280000');

-- ----------------------------
-- Table structure for pms_item
-- ----------------------------
DROP TABLE IF EXISTS `pms_item`;
CREATE TABLE `pms_item`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_item
-- ----------------------------
INSERT INTO `pms_item` VALUES ('402880e46ddd6b9c016ddd6bcf280000', '2019-10-18 13:51:33', '张三', 333, '1', '2021-03-01 17:31:22');
INSERT INTO `pms_item` VALUES ('402880e46de153b6016de15602020002', '2019-10-19 08:06:13', '李四', 333, '1', '2021-03-01 17:31:25');

-- ----------------------------
-- Table structure for pms_permission
-- ----------------------------
DROP TABLE IF EXISTS `pms_permission`;
CREATE TABLE `pms_permission`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `generate_menu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zindex` int(11) NULL DEFAULT NULL,
  `parent_permission_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `FKe1m1em88qtroc8g4ufcxjdxe5`(`parent_permission_uuid`) USING BTREE,
  CONSTRAINT `FKe1m1em88qtroc8g4ufcxjdxe5` FOREIGN KEY (`parent_permission_uuid`) REFERENCES `pms_permission` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_permission
-- ----------------------------
INSERT INTO `pms_permission` VALUES ('402880e46ded8a10016ded93b8870000', '', '2019-10-21 17:09:04', '管理员查看全部', '0', '系统管理', '2020-08-25 18:52:18', '', 1, NULL);
INSERT INTO `pms_permission` VALUES ('402880e46df612f3016df61b3eb50000', '', '2019-10-23 08:54:04', '管理项目问题', '0', '项目管理', '2019-10-23 16:22:03', '', 1, NULL);
INSERT INTO `pms_permission` VALUES ('402880e46df612f3016df61b9bbe0001', '', '2019-10-23 08:54:28', '管理员管理用户', '1', '用户管理', '2019-10-23 09:06:22', '/system/userList.do', 1, '402880e46ded8a10016ded93b8870000');
INSERT INTO `pms_permission` VALUES ('402880e46df612f3016df61c05160002', '', '2019-10-23 08:54:54', '管理员修改角色', '1', '角色管理', '2019-10-23 09:06:17', '/system/roleList.do', 1, '402880e46ded8a10016ded93b8870000');
INSERT INTO `pms_permission` VALUES ('402880e46df612f3016df61ed0b60003', '', '2019-10-23 08:57:58', '管理员管理权限', '1', '权限管理', '2019-10-23 09:06:11', '/system/permissionList.do', 1, '402880e46ded8a10016ded93b8870000');
INSERT INTO `pms_permission` VALUES ('402880e46df612f3016df62185c40004', '', '2019-10-23 09:00:55', '普通用户提交问题', '0', '问题详情', '2020-04-01 12:02:57', '/system/detailList.do', 1, '402880e46df612f3016df61b3eb50000');
INSERT INTO `pms_permission` VALUES ('402880ed6df7273f016df73c83d80000', '', '2019-10-23 14:10:01', '查看全部项目', '1', '项目详情', '2019-10-23 14:11:02', '/system/itemList.do', 1, '402880e46df612f3016df61b3eb50000');

-- ----------------------------
-- Table structure for pms_role
-- ----------------------------
DROP TABLE IF EXISTS `pms_role`;
CREATE TABLE `pms_role`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_role
-- ----------------------------
INSERT INTO `pms_role` VALUES ('402880e46df0da5f016df0df61210000', 'ADMIN', '2019-12-19 10:39:10', 'xxxxx', '管理员', '2019-12-19 10:39:10');
INSERT INTO `pms_role` VALUES ('402880e46df0da5f016df0df9b640001', 'USER', '2019-10-24 14:39:34', 'xxxxx', '普通用户', '2019-10-24 14:39:34');

-- ----------------------------
-- Table structure for pms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `pms_role_permission`;
CREATE TABLE `pms_role_permission`  (
  `pms_role_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pms_permission_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pms_role_uuid`, `pms_permission_uuid`) USING BTREE,
  INDEX `FK27sgce7kn4t45vlvtrlqumfwl`(`pms_permission_uuid`) USING BTREE,
  CONSTRAINT `FK27sgce7kn4t45vlvtrlqumfwl` FOREIGN KEY (`pms_permission_uuid`) REFERENCES `pms_permission` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKj2pscxbb647yhivhsqe77oj8q` FOREIGN KEY (`pms_role_uuid`) REFERENCES `pms_role` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_role_permission
-- ----------------------------
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df61210000', '402880e46ded8a10016ded93b8870000');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df61210000', '402880e46df612f3016df61b3eb50000');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df9b640001', '402880e46df612f3016df61b3eb50000');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df61210000', '402880e46df612f3016df61b9bbe0001');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df61210000', '402880e46df612f3016df61c05160002');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df61210000', '402880e46df612f3016df61ed0b60003');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df61210000', '402880e46df612f3016df62185c40004');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df9b640001', '402880e46df612f3016df62185c40004');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df61210000', '402880ed6df7273f016df73c83d80000');
INSERT INTO `pms_role_permission` VALUES ('402880e46df0da5f016df0df9b640001', '402880ed6df7273f016df73c83d80000');

-- ----------------------------
-- Table structure for pms_user
-- ----------------------------
DROP TABLE IF EXISTS `pms_user`;
CREATE TABLE `pms_user`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_user
-- ----------------------------
INSERT INTO `pms_user` VALUES ('402880e46dd7c48c016dd7ccb08c0001', '泰安', 21, '2019-10-23 15:49:15', '1850376654@qq.com', '女', '测试1', '123', '1', '18865387560', '2019-10-23 15:49:15', 'bbb');
INSERT INTO `pms_user` VALUES ('402880e46dd8981e016dd8a445750000', '泰安', 23, '2019-10-17 15:35:07', '1231@qq.com', '男', '测试2', '123', '1', '12345678912', '2019-10-22 08:31:29', 'aaa');

-- ----------------------------
-- Table structure for pms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `pms_user_role`;
CREATE TABLE `pms_user_role`  (
  `pms_user_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pms_role_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pms_user_uuid`, `pms_role_uuid`) USING BTREE,
  INDEX `FKo1g06twojd8l07l655br33pej`(`pms_role_uuid`) USING BTREE,
  CONSTRAINT `FK87fpd5de2t5qt387nj3xj8360` FOREIGN KEY (`pms_user_uuid`) REFERENCES `pms_user` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKo1g06twojd8l07l655br33pej` FOREIGN KEY (`pms_role_uuid`) REFERENCES `pms_role` (`uuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_user_role
-- ----------------------------
INSERT INTO `pms_user_role` VALUES ('402880e46dd8981e016dd8a445750000', '402880e46df0da5f016df0df61210000');
INSERT INTO `pms_user_role` VALUES ('402880e46dd7c48c016dd7ccb08c0001', '402880e46df0da5f016df0df9b640001');

SET FOREIGN_KEY_CHECKS = 1;
