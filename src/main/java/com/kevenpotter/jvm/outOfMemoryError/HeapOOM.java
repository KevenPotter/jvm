package com.kevenpotter.jvm.outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * <p>
 * Java heap space解决方案:
 * 一般的手段是先通过内存映像分析工具(如Eclipse Memory Analyzer)对Dump出来的堆转储快照进行分析,重点是确认内存中的对象
 * 是否是必要的,也就是要先分清楚到底是出现了内存泄漏(Memory Leak)还是内存溢出(Memory Overflow).
 * <p>
 * 如果是内存泄漏,可进一步通过工具查看泄漏对象到GC Roots的引用链.于是就能找到泄漏对象是通过怎样的路径与GC Roots相关联
 * 并导致垃圾收集器无法自动回收它们的.掌握了泄漏对象的类型信息及GC Roots引用链的信息,就可以比较准确地定位出泄漏代码的位置;
 * <p>
 * 如果不存在泄漏,换句话说,就是内存中的对象确实都还必须存活着,那就应当检查虚拟机的堆参数(-Xmx与-Xms),与机器物理内存对
 * 比看是否还可以调大,从代码上检查是否存在某些对象声明周期过长、持有状态时间过长的情况,尝试减少程序运行期的内存消耗.
 */
public class HeapOOM {

    static class OOMObject {
    }

    /*
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid11312.hprof ...
     * Heap dump file created [28319381 bytes in 0.232 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     *      at java.util.Arrays.copyOf(Arrays.java:3210)
     *      at java.util.Arrays.copyOf(Arrays.java:3181)
     *      at java.util.ArrayList.grow(ArrayList.java:265)
     *      at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     *      at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     *      at java.util.ArrayList.add(ArrayList.java:462)
     *      at com.kevenpotter.jvm.outOfMemoryError.HeapOOM.main(HeapOOM.java:17)
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
