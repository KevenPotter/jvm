package com.kevenpotter.jvm.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 57.透过字节码生成审视Java动态代理运作机制
 */
public class DynamicSubject implements InvocationHandler {

    private Object sub;

    public DynamicSubject(Object obj) {
        this.sub = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling: " + method);

        method.invoke(this.sub, args);

        System.out.println("after calling: " + method);
        return null;
    }
}