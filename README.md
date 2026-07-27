自己学习的一些demo

## JWT 登录鉴权快速验证

1. 执行初始化脚本：`src/main/resources/sql/init-auth.sql`（会创建 `auth_user` 表并插入测试账号，不再改 `user` 表）
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

## RocketMQ 快速验证

1. 启动本地 RocketMQ NameServer 和 Broker，默认连接地址在 `src/main/resources/application-dev.yml`：
   - `rocketmq.name-server: 127.0.0.1:9876`
   - 默认 Topic：`one-by-one-demo-topic`
   - 默认生产者组：`one-by-one-producer-group`
   - 默认消费者组：`one-by-one-demo-consumer-group`
2. 如果 Broker 关闭了自动创建 Topic，需要先创建 `one-by-one-demo-topic`。
3. 登录获取 JWT 后发送消息：
   ```bash
   curl -X POST 'http://localhost:9011/rocketmq/send' \
     -H 'Authorization: Bearer <token>' \
     -H 'Content-Type: application/json' \
     -d '{"content":"hello rocketmq"}'
   ```
4. 消费成功后，应用日志会输出：
   ```text
   RocketMQ demo consumer received message: hello rocketmq
   ```

## RocketMQ 阻塞消费示例

这个示例用有序消息演示阻塞：同一个 `shardingKey` 的消息会进入同一个队列，消费者按顺序处理；第一条消息 `sleep` 没结束，后面的消息就不会开始消费。

1. 发送 3 条消息，每条消费时阻塞 5 秒：
   ```bash
   curl -X POST 'http://localhost:9011/rocketmq/blocking/send' \
     -H 'Authorization: Bearer <token>' \
     -H 'Content-Type: application/json' \
     -d '{"content":"watch blocking","count":3,"sleepSeconds":5}'
   ```
2. 看应用日志，能看到每条消息的 start/end 间隔约 5 秒，且下一条的 start 在上一条 end 后才出现：
   ```text
   RocketMQ blocking demo start, traceId=..., sequence=1, sleepSeconds=5, time=...
   RocketMQ blocking demo end, traceId=..., sequence=1, time=...
   RocketMQ blocking demo start, traceId=..., sequence=2, sleepSeconds=5, time=...
   RocketMQ blocking demo end, traceId=..., sequence=2, time=...
   RocketMQ blocking demo start, traceId=..., sequence=3, sleepSeconds=5, time=...
   RocketMQ blocking demo end, traceId=..., sequence=3, time=...
   ```
