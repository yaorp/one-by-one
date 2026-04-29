-- 登录认证专用表（与业务 user 表分离）
-- MySQL 8

CREATE TABLE IF NOT EXISTS auth_user (
  id         BIGINT      NOT NULL COMMENT '主键，雪花ID',
  username   VARCHAR(100) NOT NULL COMMENT '登录名',
  password   VARCHAR(100) NOT NULL COMMENT 'BCrypt 密码哈希',
  PRIMARY KEY (id),
  UNIQUE KEY uk_auth_user_username (username)
) COMMENT='登录鉴权用户';

-- 测试账号：admin / 123456（id 为便于本地联调的固定值；应用内新增用户由雪花算法生成）
-- BCrypt("123456") = $2a$10$b516ublgNOOJvRx37a6yj.5YyuPJsJNcCiMJulpClBDbrpIbcLwyS
INSERT INTO auth_user (id, username, password)
VALUES (1987654321098123456, 'admin', '$2a$10$b516ublgNOOJvRx37a6yj.5YyuPJsJNcCiMJulpClBDbrpIbcLwyS')
ON DUPLICATE KEY UPDATE password = VALUES(password);
