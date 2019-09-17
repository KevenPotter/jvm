package com.kevenpotter.jvm.outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM1 {

    /*
     * 实际运行结果:
     * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
     * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=10M; support was removed in 8.0
     * 预期运行结果:
     * Exception in thread "main" java.lang.OutOfMemoryError: PremGen space
     *   at java.lang.String.intern(Native Method)
     *   at com.kevenpotter.jvm.outOfMemoryError.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:26)
     */
    public static void main(String[] args) {
        // 使用List保持着常量池引用,避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
