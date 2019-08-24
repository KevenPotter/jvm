/**
 * 19.自定义类加载器在复杂类加载情况下的运行分析
 */
package com.kevenpotter.jvm.classloader;

public class MyTest18 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyTest16 loader1 = new MyTest16("loader1");
        Class<?> clazz = loader1.loadClass("com.kevenpotter.jvm.classloader.MySample");
        System.out.println(clazz.hashCode());
        /*
         * 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
         * 因此不会实例化MyCat对象，即没有对MyCat进行主动使用，这里就不会加载MyCat Class
         */
        Object object = clazz.newInstance();
    }
}