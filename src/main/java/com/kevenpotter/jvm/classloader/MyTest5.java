/**
 * 08.接口初始化规则与类加载器准备阶段和初始化阶段的重要意义分析
 * 当一个接口初始化时，并不要求其父接口都完成了初始化
 * 只有在真正使用到父接口的时候(如引用接口中所定义的常量时)，才会初始化
 */
package com.kevenpotter.jvm.classloader;

public class MyTest5 {

    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

interface MyParent5 {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent5 invoked");
        }
    };
}

interface MyChild5 extends MyParent5 {
    public static int b = 6;
//    public static int b = new Random().nextInt(2);
}
