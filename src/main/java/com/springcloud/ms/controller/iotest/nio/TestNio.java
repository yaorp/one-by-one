package com.springcloud.ms.controller.iotest.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: yaorp
 */
public class TestNio {

    @Test
    public void test1() throws Exception{
        // 创建输出流
        FileOutputStream fos = new FileOutputStream("basic.txt");
        // 从流中得到一个通道
        FileChannel fc = fos.getChannel();
        // 提供一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 往缓冲区中存入数据
        String str = "HelloJava";
        buffer.put(str.getBytes());

        // pos初始大小为9 lim最大值
        // 5 翻转缓冲区
        buffer.flip();
        // pos 初始大小为0 lim 9
        // 6。把缓冲区写到通道中，从pos--> lim
        fc.write(buffer);
        // 关闭
        fos.close();

    }

    @Test
    public void test2() throws Exception{
        File file = new File("basic.txt");
        // 创建输入流
        FileInputStream fis = new FileInputStream(file);
        // 2。得到一个通道
        FileChannel fc = fis.getChannel();
        // 3。准备一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        // 4。从通道里读取数据并存到缓冲区中
        fc.read(buffer);
        System.out.println(new String(buffer.array()));
        // 5。关闭
        fis.close();
    }

    @Test
    public void test3()  throws Exception{
        FileInputStream fis = new FileInputStream("basic.txt");
        FileOutputStream fos = new FileOutputStream("basic2.txt");

        byte[] b = new byte[1024];
        while (true){
            int res = fis.read(b);
            if (res == -1){
                break;
            }

            System.out.println("res:"+res);
            fos.write(b,0,res);
        }
    }

    @Test
    public void test4() throws Exception{
        // 1。创建两个流
        FileInputStream fis = new FileInputStream("basic2.txt");
        FileOutputStream fos = new FileOutputStream("basic3.txt");
        // 2。得到两个通道
        FileChannel sourceFc = fis.getChannel();
        FileChannel destFc = fos.getChannel();
        // 3。复制
        destFc.transferFrom(sourceFc,0, sourceFc.size());
        // 4。关闭
        fis.close();
        fos.close();
    }

}
