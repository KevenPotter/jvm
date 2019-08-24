/**
 * 21.类加载器实战剖析与疑难点解析
 */
package com.kevenpotter.jvm.classloader;

public class MyTest18_2 {

    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}