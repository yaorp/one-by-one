-- 登录鉴权最小初始化脚本（MySQL 8）
-- 1) 给 user 表补 password 字段（若已存在请忽略这条报错）
ALTER TABLE user ADD COLUMN password VARCHAR(100) NULL COMMENT 'BCrypt密码哈希';

-- 2) 为用户名增加唯一索引（若已存在请忽略这条报错）
ALTER TABLE user ADD UNIQUE INDEX uk_user_username (username);

-- 3) 初始化测试账号：admin / 123456
-- BCrypt("123456") = $2a$10$b516ublgNOOJvRx37a6yj.5YyuPJsJNcCiMJulpClBDbrpIbcLwyS
INSERT INTO user (id, username, password, birthday, sex, address)
VALUES ('1', 'admin', '$2a$10$b516ublgNOOJvRx37a6yj.5YyuPJsJNcCiMJulpClBDbrpIbcLwyS', NULL, NULL, NULL)
ON DUPLICATE KEY UPDATE password = VALUES(password);
