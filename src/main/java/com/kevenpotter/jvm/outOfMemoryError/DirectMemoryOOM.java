package com.kevenpotter.jvm.outOfMemoryError;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    /*
     * Exception in thread "main" java.lang.OutOfMemoryError
     *    at sun.misc.Unsafe.allocateMemory(Native Method)
     *    at com.kevenpotter.jvm.outOfMemoryError.DirectMemoryOOM.main(DirectMemoryOOM.java:19)
     * 由DirectMemory导致的内存溢出,一个明显的特征实在Heap Dump文件中不会看见明显的异常,如果读者发现OOM之后Dump文件很小,而
     * 程序中又直接或间接使用了NIO,那就可以考虑检查一下是不是这方面的原因.
     */
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
