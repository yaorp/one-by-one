package com.springcloud.ms.controller.iotest;

import lombok.Data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: yaorp
 */
public class TransientTest {

    public static void main(String[] args) throws IOException {
//        User user = new User();
//        user.setUserName("yaorp");
//        user.setPassword("111111");
//        System.out.println("序列化前：" + user.toString());
//
//        ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(Paths.get("/Users/yaorp/Downloads/transientTest.txt")));
//        os.writeObject(user);
//        os.flush();
//        os.close();

        ObjectInputStream is = new ObjectInputStream(Files.newInputStream(Paths.get("/Users/yaorp/Downloads/transientTest.txt")));
        try {
            User user1 = (User) is.readObject();
            System.out.println("反序列化后：" + user1.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

@Data
class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private transient String password;


}
