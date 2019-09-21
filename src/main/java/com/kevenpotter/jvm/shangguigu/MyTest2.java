package com.kevenpotter.jvm.shangguigu;

/**
 * 三种JVM(JVM被翻译为Java虚拟机无可厚非,但是实际上这是一种Java虚拟机规范.对于JVM还有其不同的实现)
 * Sun(已被Oracle收购)公司的HotSpot
 * BEA(已被Oracle收购)公司的JRockit
 * IBM公司的J9VM
 * <p>
 * Heap堆内存:
 * 新生代:伊甸区(Eden Space)、幸存区0(Survivor 0 Space)、幸存区1(Survivor 1 Space)
 * 老年代:Tenure Generation Space
 * 永久代(Java8已经替换为元数据区):Permanent Space,这里没有垃圾回收.永久代是一个常驻内存区域,用于存放JDK自身所携带的Class、
 * Interface的元数据,也就是说它存储的是运行环境必须的类信息.被装载进此区域的数据是不会被垃圾回收器回收掉的.关闭JVM才会释放
 * 此区域所占用的内存
 * ·伊甸区(Eden Space):这里存放new出来的对象
 * ·老年代(Tenure Generation Space):一般这里存放数据库连接池对象
 * ·永久代(Permanent Space):存放JDK自身携带的Class、Interface
 * <p>
 * 如果出现java.lang.OutOfMemoryError:Java heap space异常,说明Java虚拟机的堆内存不够.原因有二:
 * 1.Java虚拟机的堆内存设置不够,可以通过参数-Xms、-Xmx来调整;
 * 2.代码中创建了大量大对象,并且长时间不能被垃圾收集器收集(存在被引用)
 * <p>
 * 如果出现java.lang.OutOfMemoryError:PermGen space异常,说明Java虚拟机对永久代Perm内存设置不够.一般出现这种情况,都是程
 * 序启动需要加载大量的第三方jar包.例如:在一个Tomcat下部署了太多的应用,或者大量动态反射生成的类不断被加载,最终导致Perm区
 * 被占满
 * JDK1.6及之前:有永久代,常量池1.6在方法区;
 * JDK1.7:      有永久代,但已经逐步"去永久代",常量池1.7在堆;
 * JDK1.8及之后:无永久代,常量池1.8在元空间
 * <p>
 * 实际而言,方法区(Method Area)和堆一样,是各个线程共享的内存区域,它用于存储虚拟机加载的:类信息+普通常量+静态变量+编译器
 * 编译后的代码等等.虽然JVM规范将方法区描述为堆得一个逻辑部分,但它却还有一个别名叫做非堆(Non-heap),目的就是要和堆分开
 * 对于HotSpot虚拟机,很多开发者习惯将方法区称之为"永久代(Permanent Space)",但严格本质上说两者是不同的,或者说使用永久代
 * 来实现方法区而已.永久代是方法区(相当于一个借口Interface)的一个实现,JDK1.7的版本中,已经将原本放在永久代的字符串常量池
 * 移走
 * 常量池(Constant Pool)是方法区的一部分,Class文件除了有类的版本、字段、方法、接口等描述信息外,还有一项信息就是常量池,
 * 这部分内容在类加载后进入方法区的运行时常量池中存放
 * <p>
 * 堆内存调优简介
 * -Xms(start):设置初始分配大小,默认为物理内存的1/64
 * -Xmx(max):设置最大分配内存,默认为物理内存的1/4
 * -XX:PrintGCDetails:输出详细的GC处理日志
 * <p>
 * GC是什么
 * 频繁收集YONG区
 * 较少收集OLD区
 * 基本不动PERM区
 * JVM在进行GC时,并非每次都对三个内存区域一起回收,大部分时候回收的都是指新生代.因此GC按照回收的区域又分为了两种类型,一种
 * 是普通GC(minor GC),一种是全局GC(major GC OR full GC).
 * 1.普通GC(minor GC):只针对新生代区域的GC
 * 2.全局GC(major GC OR full GC):针对老年代的GC,偶尔伴随对新生代的GC以及对永久代的GC
 * <p>
 * GC四大算法
 * 1.引用计数法(不用),因为解决不了双端循环引用问题;
 * 2.复制算法minor GC:新生代用的是复制算法(Copying),Minor GC会把Eden中的所有活的对象都移动到Survivor区域中,如果Survivor
 * 区中放不下,那么剩下活的对象就被移到Old generation中,也即一旦收集后,Eden区就变成空的了.
 * 当对象在Eden(包括一个Survivor区域,这里假设是From区域)出生后,在经过一次Minor GC后,如果对象还存活,并且能够被另外一块
 * Survivor区域所容纳(上面已经假设为From区域,这里应为To区域,即To区域有足够的内存空间来存储Eden和From区域中存活的对象),
 * 则使用复制算法将这些仍然还存活的对象复制到另外一块Survivor区域(即To区域中),然后清理所使用过的Eden以及Survivor区域(
 * 即From区域),并且将这些对象的年龄设置为1,以后对象在Survivor区域每熬过一次Minor GC,就将对象的年龄+1,当对象的年龄达到
 * 某个值时(默认是15岁,通过-XX:MaxTenuringThreshold来设定参数),这些对象就会成为老年代.复制算法不会产生内存碎片.经过此
 * 次GC后,Eden区和From区已经被清空.这个时候From和To会交换它们的角色,也就是新的To就是上次GC前的From,新的From就是上次GC
 * 前的To.
 * 因为Eden区对象一般存活率较低,一般的,使用两块10%的内存作为空闲和活动区间,而另外80%的内存,则是用来给新建对象分配内存的.
 * 一旦发生GC,将10%的活动区间与另外80%中存活的对象转移到10%的空闲区间,接下来,将之前90%的内存全部释放,以此类推......
 * Minor GC缺点:复制算法弥补了标记/清除算法中内存布局混乱的缺点.不过与此同时,它的缺点也是相当明显的:
 * ·1.它浪费了一半的内存,这太要命了;
 * ·2.如果对象的存活率很高,我们可以极端一点,假设是100%存活,那么我们需要将所有对象都复制一遍,并将所有引用地址重置一遍.复
 * 制这一工作所花费的时间,在对象存活率达到一定程度时,将会变得不可忽视.所以从以上描述不难看出,复制算法要想使用,最起码对象
 * 的存活率要非常低才行,而且最重要的是,我们必须要克服50%内存的浪费.这里的50%针对的是811比例中的11来说的.
 * 3.标记清除/标记整理算法major GC OR full GC:
 */
public class MyTest2 {

    public static void main(String[] args) {
        // 返回Java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        // 返回Java虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("MAX_MEMORY = " + maxMemory + " 字节," + (maxMemory / (double) 1024 / 1024) + " MB");
        System.out.println("TOTAL_MEMORY = " + totalMemory + " 字节," + (totalMemory / (double) 1024 / 1024) + " MB");
    }
}
