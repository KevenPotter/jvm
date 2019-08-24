/**
 * 05.类的加载连接与初始化过程详解
 * 演示主动使用和被动使用:
 * 对于静态字段来说，只有直接定义了该字段的类才会被初始化
 * 当一个在初始化时，要求其父类全部都已经初始化完毕了
 * <p>
 * 用于追踪类的加载信息并打印出来：-XX:+TraceClassLoading
 * <p>
 * JVM参数的三种情况
 * -XX:+<option>    表示开启option选项
 * -XX:-<option>    表示关闭option选项
 * -XX:<option>=<value>    表示将option选项的值设置为value
 */
package com.kevenpotter.jvm.classloader;

public class MyTest1 {

    public static void main(String[] args) {
        System.out.println(MyChild1.str);
    }
}

class MyParent1 {

    public static String str = "hello world";

    static {
        System.out.println("MyPatent1 static block");
    }
}

class MyChild1 extends MyParent1 {

    public static String str2 = "welcome";

    static {
        System.out.println("MyChild static block");
    }
}
