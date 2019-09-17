package com.kevenpotter.jvm.outOfMemoryError;

/**
 * -Xss128K
 * 关于虚拟机栈和本地方法栈,在Java虚拟机规范中描述了两种异常:
 * 1.如果线程请求的栈深度大于虚拟机所允许的最大深度,将抛出StackOverflowError异常;
 * 2.如果虚拟机在扩展栈时无法申请到足够的内存空间,则抛出OutOfMemoryError异常
 */
public class JavaVMStackSOF1 {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /*
     * stack length:1403
     * Exception in thread "main" java.lang.StackOverflowError
     *   at com.kevenpotter.jvm.outOfMemoryError.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
     *   at com.kevenpotter.jvm.outOfMemoryError.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
     *   at com.kevenpotter.jvm.outOfMemoryError.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
     *   at com.kevenpotter.jvm.outOfMemoryError.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
     * ......后续异常堆栈信息省略
     */
    public static void main(String[] args) {
        JavaVMStackSOF1 oom = new JavaVMStackSOF1();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
