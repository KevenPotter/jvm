package com.kevenpotter.jvm.memory;

/**
 * 60.JVM内存空间划分与作用详解
 * 虚拟机栈:Stack Frame 栈帧
 * 程序计数器(Program Counter):
 * 本地方法栈:主要用于处理(执行)本地方法
 * 堆(Heap):JVM管理的最大一块内存空间
 * 方法区(Method Area):存储元信息(如常量、静态变量、类本身所固有的信息等等).永久代(permanent generation),从JDK 1.8开始,
 * 已经彻底废弃了永久代,使用元空间(META SPACE)进行替代.
 * 运行时常量池:方法区的一部分.
 * 直接内存:Direct Memory
 */
public class MyTest1 {
}
