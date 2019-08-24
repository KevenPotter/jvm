/**
 * 12.类加载器与类初始化深度剖析
 */
package com.kevenpotter.jvm.classloader;

class Parent2 {

    static int a = 3;

    static {
        System.out.println("Parent2 static block");
    }
}

class Child2 extends Parent2 {

    static int b = 4;

    static {
        System.out.println("Child2 static block");
    }
}

public class MyTest10 {

    static {
        System.out.println("MyTest10 static block");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Parent2 parent2;
        System.out.println("----------");
        parent2 = new Parent2();
        System.out.println("----------");
        System.out.println(parent2.a);
        System.out.println("----------");
        System.out.println(Child2.b);
    }
}