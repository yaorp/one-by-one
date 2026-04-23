自己学习的一些demo

## JWT 登录鉴权快速验证

1. 执行初始化脚本：`src/main/resources/sql/init-auth.sql`
2. 启动应用后调用登录接口：
   - `POST /auth/login`
   - Body:
     ```json
     {
       "username": "admin",
       "password": "123456"
     }
     ```
3. 获取 `token` 后访问受保护接口，例如：
   - `GET /user/queryUser?id=1`
   - Header: `Authorization: Bearer <token>`

## 一键联调脚本

- 脚本路径：`scripts/auth-smoke.sh`
- 用法（默认 `http://localhost:9011 admin/123456 userId=1`）：
  - `bash scripts/auth-smoke.sh`
- 自定义参数：
  - `bash scripts/auth-smoke.sh <baseUrl> <username> <password> <userId>`
