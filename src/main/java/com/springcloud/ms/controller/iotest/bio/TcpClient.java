package com.springcloud.ms.controller.iotest.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: yaorp
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {

        while (true) {
            // 创建socket 对象
            Socket s = new Socket("127.0.0.1",9999);
            // 从连接中取出输出流，并发消息
            OutputStream os = s.getOutputStream();
            System.out.println("请输入：");
            Scanner scanner = new Scanner(System.in);
            String s1 = scanner.nextLine();
            // 字节数组形式输出
            os.write(s1.getBytes());
            // 从连接中取出输入流，并接收回话
            InputStream is = s.getInputStream();
            byte[] b = new byte[20];
            is.read(b);
            System.out.println("老板说："+new String(b).trim());
        }

    }
}
