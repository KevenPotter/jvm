package com.kevenpotter.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 * 49.通过字节码分析this关键字以及异常表的重要作用
 * args_size=1的原因:
 * 对于Java类中的每一个实例方法(非static方法),其在编译后所生成的字节码当中,方法参数的数量总是会比源代码中方法参数的数量
 * 多一个(this).它位于方法的第一个参数位置处;这样,我们就可以在Java的实例方法中使用this来去访问当前对象的属性以及其他方
 * 法.这个操作是在编译期间完成的,即由javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问.接下来在运
 * 行期间,由JVM在调用实例方法时,自动向实例方法传入该this参数.所以,在实例方法的局部变量表中,至少会有一个指向当前对象的局
 * 部变量.
 * <p>
 * locals=4的原因:this,is,serverSocket,e
 * 50.通过字节码分析Java异常处理机制
 * Java字节码对于异常处理的方式:
 * 1.统一采用异常表的方式来对异常进行处理;
 * 2.在之前JDK 1.4.2之前的版本中,并不是使用异常表的方式来对异常进行处理的,而是采用特定的指令方式;
 * 3.当异常处理存在finally语句块时,现代化的JVM采取的处理方式是将finally语句块的字节码拼接到每一个catch块后面,换句话说,
 * 程序中存在多少个catch块,就会在每一个catch块后面重复多少个finally语句块的字节码.
 */
public class MyTest3 {

    public void test() throws IOException, FileNotFoundException, NullPointerException {
        try {
            InputStream is = new FileInputStream("test.txt");
            ServerSocket serverSocket = new ServerSocket(9999);
            serverSocket.accept();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        } finally {
            System.out.println("finally");
        }
    }
}