package com.kevenpotter.jvm.outOfMemoryError;

/**
 * -Xss2m(这时候不妨设置大些)
 * 注意:一定要保存当前的工作.由于在Windows平台的虚拟机中,Java的线程是映射到操作系统的内核线程上的,因此上述代码执行时有较大的
 * 风险,可能会导致操作系统假死.
 */
public class JavaVMStackSOF2 {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    /*
     * Exception in thread "main" java.lang.OutOfMemoryError: Unable to create new native thread
     */
    public static void main(String[] args) {
//        JavaVMStackSOF2 oom = new JavaVMStackSOF2();
//        oom.stackLeakByThread();
    }
}
