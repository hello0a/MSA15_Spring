-- Active: 1767840773547@@127.0.0.1@3306@aloha
CREATE DATABASE IF NOT EXISTS aloha;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `no` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',
  `id` varchar(64) NULL UNIQUE COMMENT 'UK',
  `name` varchar(100) NOT NULL COMMENT '상품명',
  `price` int NOT NULL COMMENT '가격',
  `stock` int NOT NULL COMMENT '재고',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
) COMMENT '상품';

TRUNCATE Table product;

INSERT INTO `product` ( `id`, `name`, `price`, `stock` )
VALUES 
  ( UUID(), '고구마', 2000, 13),
  ( UUID(), '감자', 2000, 5),
  ( UUID(), '옥수수', 3000, 10)
;