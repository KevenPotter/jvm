/**
 * 21.类加载器实战剖析与疑难点解析
 */
package com.kevenpotter.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

public class MyTest19 {

    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest19.class.getClassLoader());
    }
}
