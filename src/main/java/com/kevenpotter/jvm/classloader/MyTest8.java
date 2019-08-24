/**
 * 12.类加载器与类初始化深度剖析
 */
package com.kevenpotter.jvm.classloader;

class FinalTest {

    public static final int x = 3;

    static {
        System.out.println("FinalTest static block");
    }
}

public class MyTest8 {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(FinalTest.x);
    }
}