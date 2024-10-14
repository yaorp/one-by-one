package com.springcloud.ms.controller.javabase.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author: yaorp
 */
public class DemoClassLoader extends ClassLoader{
    private String classpath;

    public DemoClassLoader(String classpath){
        this.classpath=classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = getData(name);
            if (classData !=null) {
                return defineClass(name,classData,0,classData.length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return super.findClass(name);
    }

    private byte[] getData(String className) throws IOException{
        String path = classpath+ File.separator+className.replace('.',File.separatorChar)+".class";
        FileInputStream in = new FileInputStream(path);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = in.read(buffer)) !=-1){
            out.write(buffer,0,len);
        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        DemoClassLoader dClassLoader = new DemoClassLoader("/Users/yaorp/code/yaorp/one-by-one/target/classes");
        Class<?> c = dClassLoader.loadClass("com.springcloud.ms.controller.javabase.classloader.CLTest");
        if (c!=null){
            Object o = c.newInstance();
            Method method = c.getMethod("say", null);
            method.invoke(o,null);
            System.out.println(c.getClassLoader().toString());
        }
    }
}
