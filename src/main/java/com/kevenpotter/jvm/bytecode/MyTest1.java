package com.kevenpotter.jvm.bytecode;

/**
 * 37.Java字节码文件结构解析
 * 1.使用javap -verbose命令分析一个字节码文件时,将会分析该字节码文件的魔数、版本号、常量池、类信息、类的构造方法、类中的方法
 * 信息、类变量与成员变量等信息;
 * 2.魔数(magic number):所有的.class文件字节码文件的前四个字节都是魔数,魔数值为固定值:OxCAFEBABE
 */
public class MyTest1 {

    private int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}