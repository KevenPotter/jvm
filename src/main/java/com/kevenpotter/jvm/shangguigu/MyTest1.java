package com.kevenpotter.jvm.shangguigu;

/**
 * Java是一门先编译后解释型的混合型语言,x.class文件通过[类加载器(类装载器)]装载进[运行时数据区(内存区域)].
 * 1.Class Loader类加载器;
 * 2.Execution Engine执行引擎负责解释命令,提交操作系统执行;
 * 3.Native Interface本地接口(了解即可,因为大致十年前就不用了,当然现在肯定在用,说不用只不过代表如今屏蔽到更底层而已);
 * 4.Runtime data area 运行时数据区,在这里,它会抛出{@link RuntimeException}
 * ·Class Loader类加载器:
 * ··负责加载class文件,class文件在文件开头有特定的文件标示,并且ClassLoader只负责class文件的加载,至于它是否可以运行,则由
 * Execution Engine决定;
 * ·Native Interface
 * ··本地接口的作用是融合不同的编程语言为Java所用,它的初衷是融合C/C++程序,Java诞生的时候是C/C++横行的时候,要想立足,必须
 * 有调用C/C++程序,于是就在内存中专门开辟了一块区域处理标记为native的代码.它的具体做法是Native Method Stack中登记native
 * 方法,在Execution Engine执行时加载native libraries
 * ··目前,该方法使用的越来越少了,除非是与硬件有关的应用,比如通过Java程序驱动打印机,或者Java系统管理生产设备,在企业级应用
 * 中已经比较少见
 * ··因为现在的异构领域间的通信很发达,比如可以使用Socket通信,也可以使用WebService等等,不多做介绍
 * <p>
 * ·Method Area方法区([运行时数据区(内存区域)]中的)
 * ··方法区是被所有线程共享.所有字段和方法字节码,以及一些特殊方法如构造函数,接口代码也在此定义.简单说,所有定义的方法的信
 * 息都保存在该区域,此区域属于共享区间
 * ··静态变量+常量+类信息+运行时常量池存放在方法区中——实例变量存在堆内存中
 * ·PC Register程序计数器([运行时数据区(内存区域)]中的)
 * ··每个线程都有一个程序计数器,就是一个指针,指向方法区中的方法字节码(下一个将要执行的指令代码),由执行引擎读取下一条指令,
 * 是一个非常小的内存空间,几乎可以忽略不计
 * ·Native Method Stack本地方法栈([运行时数据区(内存区域)]中的)
 * ··它的具体做法是Native Method Stack中登记native方法,在Execution Engine执行时加载native libraries
 * <p>
 * JVM优化99%优化堆,1%优化方法区,因为这两块区域属于线程共享!!!!!!!!!!!!!!!!!!!!!!!!!!
 * <p>
 * !!!   栈管运行,堆管存储   !!!
 * ·Stack栈是什么
 * ··栈也叫栈内存,主管Java程序的运行,是在线程创建时创建,它的生命期是跟随线程的生命期,线程结束栈内存也就释放.对于栈来说不
 * 存在垃圾回收问题,只要线程一结束该栈就Over,生命周期和线程一致,是线程私有的.基本类型的变量和对象的引用变量都是在函数的栈内
 * 存中分配.
 * ·栈存储什么
 * ··栈帧中主要保存3类数据:
 * ··1.本地变量(Local Variables):输入参数和输出参数以及方法内的变量;
 * ··2.栈操作(Operated Stack):记录出栈、入栈的操作;
 * ··3.栈帧数据(Frame Data):包括类文件、方法等
 * ·栈运行原理
 * ··栈中的数据都是以栈帧(Stack Frame)的格式存在,栈帧是一个内存区块,是一个数据集,是一个有关方法(Method)和运行期数据的数
 * 据集.当一个方法A被调用时就产生了一个栈帧F1,并被压入到栈中.A方法又调用了B方法,于是产生栈帧F2也被压入栈,B方法又调用了C方
 * 法,于是产生栈帧F3也被压入栈......执行完毕后,先弹出F3栈帧,再弹出F2栈帧,再弹出F1栈帧......遵循"先进后出/后进先出"原则
 * ··每执行一个方法都会产生一个栈帧,保存到栈(后进先出)的顶部,顶部栈就是当前的方法,该方法执行完毕后会自动将此栈帧出栈
 */
public class MyTest1 {
}
