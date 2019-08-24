/**
 * 13.不同的类加载器作用与加载动作分析
 */
package com.kevenpotter.jvm.classloader;

public class MyTest13 {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}