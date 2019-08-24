/**
 * 11.类加载器双亲委托机制详解
 */
package com.kevenpotter.jvm.classloader;

public class MyTest7 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());
        Class<?> clazz2 = Class.forName("com.kevenpotter.jvm.classloader.C");
        System.out.println(clazz2.getClassLoader());
    }
}

class C {

}