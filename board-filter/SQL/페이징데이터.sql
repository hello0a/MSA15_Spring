-- 샘플 데이터
TRUNCATE TABLE `board`;
TRUNCATE TABLE `file`;

INSERT INTO `board` ( id, title, writer, content, created_at, updated_at )
SELECT
    UUID() AS id,
    CONCAT('제목', LPAD(n, 4, '0')) AS title,
    CONCAT('작성자', LPAD(n, 4, '0')) AS writer,
    CONCAT('내용', LPAD(n, 4, '0')) AS content,
    DATE_ADD(CURDATE() - INTERVAL 1 DAY, INTERVAL n DAY) AS created_at,
    DATE_ADD(CURDATE() - INTERVAL 1 DAY, INTERVAL n DAY) AS updated_at
FROM
    (
        -- 1~1000 까지
        WITH RECURSIVE seq AS (
            SELECT 1 AS n
            UNION ALL
            SELECT n + 1 FROM seq WHERE n < 1000
        )
        SELECT n FROM seq
    ) AS numbers
;

SELECT * FROM board;