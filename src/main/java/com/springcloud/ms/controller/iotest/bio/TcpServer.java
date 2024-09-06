package com.springcloud.ms.controller.iotest.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: yaorp
 */
public class TcpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动....");
        System.out.println("初始化端口 9999");
        ServerSocket ss = new ServerSocket(9999);


        while (true) {
            // 监听客户端
            // 阻塞
            Socket s = ss.accept();
            // 从监听中取出输入流来接收消息
            // 阻塞
            InputStream is = s.getInputStream();
            byte[] b = new byte[10];
            is.read(b);
            String clientIp = s.getInetAddress().getHostAddress();
            System.out.println(clientIp+"说："+new String(b).trim());
            // 从连接中取出输出流并回话
            OutputStream os = s.getOutputStream();
            os.write("没钱".getBytes());
            // 关闭
            s.close();
        }

    }
}
