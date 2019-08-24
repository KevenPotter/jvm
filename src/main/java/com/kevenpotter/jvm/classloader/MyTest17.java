/**
 * 18.类的命名空间与卸载详解及jvisualvm使用
 */
package com.kevenpotter.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyTest17 extends ClassLoader {

    private String classLoaderName;
    private String path;
    private String fileExtension = ".class";

    public MyTest17(String classLoaderName) {
        super();    //将系统类加载器当做该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest17(ClassLoader parent, String classLoaderName) {
        super(parent);    //显式指定该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "[" + this.classLoaderName + "]";
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + this.classLoaderName);
        byte[] data = this.loadClassData(className);
        return this.defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        className = className.replace(".", "/");
        try {
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();
            int ch;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        MyTest17 loader1 = new MyTest17("load1");
        loader1.setPath("C:/Users/Administrator/Desktop/");
        Class<?> clazz = loader1.loadClass("com.kevenpotter.jvm.classloader.MyTest1");
        System.out.println("class: " + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        loader1 = null;
        clazz = null;
        object = null;
        System.gc();
        Thread.sleep(30000);
        System.out.println();
        loader1 = new MyTest17("loader1");
        loader1.setPath("C:/Users/Administrator/Desktop/");
        clazz = loader1.loadClass("com.kevenpotter.jvm.classloader.MyTest1");
        System.out.println("class: " + clazz.hashCode());
        object = clazz.newInstance();
        System.out.println(object);
    }
}