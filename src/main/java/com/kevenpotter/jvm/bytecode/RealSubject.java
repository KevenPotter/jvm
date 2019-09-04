package com.kevenpotter.jvm.bytecode;

/**
 * 57.透过字节码生成审视Java动态代理运作机制
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("From real subject");
    }
}