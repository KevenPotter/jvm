/**
 * 13.不同的类加载器作用与加载动作分析
 */
package com.kevenpotter.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest14 {

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resourceName = "com/kevenpotter/jvm/classloader/MyTest13.class";
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        System.out.println("----------");

        Class<?> clazz = MyTest14.class;
        System.out.println(clazz.getClassLoader());
    }
}