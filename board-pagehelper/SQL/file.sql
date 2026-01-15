-- 파일 테이블
CREATE TABLE `file` (
  `no` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `id` varchar(64) DEFAULT NULL UNIQUE,
  `parent_table` varchar(100) NOT NULL,
  `parent_no` int NOT NULL,
  `name` text NOT NULL,
  `path` text NOT NULL,
  `size` bigint default NULL,
  `content_type` varchar(100) default 'application/octet-stream', -- image/png, application/pdf ...
  `sort_order` int default 0,
  `is_main` tinyint(1) default false,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 파일 중복 테스트 : 가장 큰 최대값 sort_order 나옴 (3번이니까 4번부터 집어넣어야함)
-- NULL 이라면 0부터 있다면 sort_order 최대값부터
SELECT IFNULL( MAX(sort_order) + 1, 0 ) AS sort_order
    FROM file
    WHERE parent_table = :parentTable
        AND parent_no = 18
;