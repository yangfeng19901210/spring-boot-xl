/*
 Navicat Premium Dump SQL

 Source Server         : 119-8038
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : 192.168.2.119:3306
 Source Schema         : test_db

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 29/08/2025 17:30:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '产品唯一标识',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品类别',
  `price` decimal(10, 2) NOT NULL COMMENT '产品价格',
  `stock_quantity` int NOT NULL DEFAULT 0 COMMENT '库存数量',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '产品详细描述',
  `is_available` tinyint(1) NULL DEFAULT 1 COMMENT '是否可用（1:可用, 0:不可用）',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `chk_price` CHECK (`price` >= 0),
  CONSTRAINT `chk_stock` CHECK (`stock_quantity` >= 0)
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '12121', '558', 12.00, 25, NULL, 1, '2025-08-29 11:48:27', '2025-08-29 11:48:27');
INSERT INTO `product` VALUES (2, '测试产品', '测试类别', 125.00, 100, '这是一个测试产品', 1, '2025-08-29 14:17:40', '2025-08-29 14:17:40');

SET FOREIGN_KEY_CHECKS = 1;
