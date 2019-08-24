/**
 * 20.类加载器命名空间实战剖析与透彻理解
 * 关于命名空间的重要说明
 * 1.子加载器所加载的类能够访问父加载器所加载的类
 * 2.父加载器所加载的类无法访问到子加载器所加载的类
 */
package com.kevenpotter.jvm.classloader;

public class MyTest18_1 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("C:/Users/Administrator/Desktop/");
        Class<?> clazz = loader1.loadClass("com.kevenpotter.jvm.classloader.MySample");
        System.out.println(clazz.hashCode());
        Object object = clazz.newInstance();
    }
}