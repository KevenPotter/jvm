package com.kevenpotter.jvm.gc;

/**
 * -XX:+PrintGCDetails
 */
public class ReferenceCountingGC {

    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /*这个成员属性的唯一意义就是占点内存,以便能在GC日志中看清楚是否被回收过*/
    private byte[] bigSize = new byte[2 * _1MB];

    /*
     * 通过运行结果可以明白,虚拟机并没有因为这两个对象互相引用就不回收它们,这也从侧面说明了虚拟机并不是通过"引用计数算法"来
     * 判断对象是否存活的.
     *
     * 对于"可达性分析(Reachability Analysis)"算法,可作为GC Roots的对象包括下面几种:
     * 1.虚拟机栈(栈帧中的本地变量表)中引用的对象;
     * 2.方法区中类静态属性引用的对象;
     * 3.方法区中常量引用的对象;
     * 4.本地方法栈中JNI(即一般说的Native方法)引用的对象.
     */
    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }
}
