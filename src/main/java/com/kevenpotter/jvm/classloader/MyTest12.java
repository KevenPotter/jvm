/**
 * 12.类加载器与类初始化深度剖析
 * 调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
 */
package com.kevenpotter.jvm.classloader;

class CL {
    static {
        System.out.println("Class CL");
    }
}

public class MyTest12 {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = loader.loadClass("com.kevenpotter.jvm.classloader.CL");
        System.out.println(clazz);
        System.out.println("----------");
        clazz = Class.forName("com.kevenpotter.jvm.classloader.CL");
        System.out.println(clazz);
    }
}