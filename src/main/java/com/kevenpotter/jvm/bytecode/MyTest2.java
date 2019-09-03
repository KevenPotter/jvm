package com.kevenpotter.jvm.bytecode;

/**
 * 46.synchronized关键字所生成的字节码详细分析
 * javap -verbose -p xxx.class      其中-p表示显示出private属性的方法
 */
public class MyTest2 {

    String str = "welcome";

    private int x = 5;

    public static Integer in = 10;

    private synchronized void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();
        myTest2.setX(8);
        in = 20;
    }
}