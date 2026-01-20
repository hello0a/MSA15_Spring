-- 관리자 계정
INSERT INTO `user` (id, username, password, name, email)
VALUES (UUID(), 'admin', '$2a$10$PMkZ5OE8AxstxMJi1RSHtu.Z4W2m7ZGSPTiruR1navWluw9hk6/Pq', '관리자', 'admin@naver.com');

INSERT INTO `user_auth` (id, username, auth)
VALUES 
    (UUID(), 'admin', 'ROLE_USER'),
    (UUID(), 'admin', 'ROLE_ADMIN');