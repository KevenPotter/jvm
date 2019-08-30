package com.kevenpotter.jvm.bytecode;

/**
 * 37.Java字节码文件结构解析
 * 38.Java字节码常量池深入剖析
 * 1.使用javap -verbose命令分析一个字节码文件时,将会分析该字节码文件的魔数、版本号、常量池、类信息、类的构造方法、类中的方法
 * 信息、类变量与成员变量等信息;
 * 2.魔数(magic number):所有的.class文件字节码文件的前四个字节都是魔数,魔数值为固定值:OxCAFEBABE
 * 3.魔数之后的4个字节为版本信息,前两个字节表示minor version(次版本号),后两个字节表示major version(主版本号).
 * 这里的版本号为00 00 00 34,换算成十进制,表示次版本号为0,主版本号为52.这里的52代表的意思是JDK8,所以,51就代表了JDK7.
 * 所以,该文件的版本号为1.8.0(这个0就是所谓的"次版本号").可以通过java -version命令来验证这一点.
 * 4.常量池(constant pool):紧接着主版本号之后的就是常量池入口(也就是第九个字节).一个Java类中定义的很多信息都是由常量池来维护
 * 和描述的,可以将常量池看作是Class文件的资源仓库,比如说Java类中定义的方法与变量信息,都是存储在常量池中.常量池中主要存储两类
 * 常量:字面量和符号引用.
 * 字面量:如文本字符串,Java中声明为final的常量值等......
 * 符号引用:如类和接口的全局限定名,字段的名称和描述符,方法的名称和描述符等......
 * 5.常量池的总体结构:Java类所对应的常量池主要由常量池数量与常量池数组(也可以说是表结构或常量表)这两部分共同构成.常量池数量紧
 * 跟在主版本号后面,占据2个字节;而常量池数组则紧跟在常量池数量之后.常量池数组与一般的数组不同的是,常量池数组中不同的元素的类型、
 * 结构都是不同的,长度当然也就不同;但是,每一种元素的第一个数据都是一个u1类型,该字节是个标志位,占据1个字节.JVM在解析常量池时,会
 * 根据这个u1类型来获取对应的元素的具体类型.值得注意的是,常量池数组中元素的个数=常量池数 - 1 (其中0暂时不使用),目的是满足某些
 * 常量池索引值的数据在特定情况下需要表达[不引用任何一个常量池]的含义;根本原因在于,索引为0也是一个常量(JVM的保留常量),只不过
 * 它不位于常量池数组(常量表)中,这个常量就对应null值;所以,常量池的索引从1而非0开始.
 * <p>
 * 39.透彻分析常量池常量结构与描述符
 * 6.在JVM规范中,每个变量/字段都有描述信息,描述信息的主要作用是描述字段的数据类型、方法的参数列表(包括数量、类型与顺序)与返回
 * 值.根据描述符规则,基本数据类型和代表无返回值的void类型都用一个大写字符来表示,对象类型则使用字符L加对象的全限定名称来表示.
 * 为了压缩字节码文件的体积,对于基本数据类型,JVM都只使用一个大写字母来表示,如下所示:
 * B - byte    C - char    D - double    F - float    I - int    J - long    S - short    Z - boolean    V - void    L - 对象类型
 * 如:Ljava/lang/String;
 * 7.对于数组类型来说,每一个维度使用一个前置的[来表示,如int[]被记录为[I,String[][]被记录为[[Ljava/lang/String;
 * 8.用描述符描述方法时,按照先参数列表,后返回值的顺序来描述.参数列表按照参数的严格顺序放在一组()之内,
 * 如方法String getRealNameByIdAndNickName(int id,String name)的描述符为:
 * (I,Ljava/lang/String;) Ljava/lang/String;
 * <p>
 * 40.Java字节码常量池深度剖析与字节码整体结构分解
 * 41.字节码访问标志与字段表详解
 * ClassFile{
 * u4               magic;
 * u2               minor_version;
 * u2               major_version;
 * u2               constant_pool_count;
 * cp_info          constant_pool[constant_pool_count - 1];
 * u2               access_flags;
 * u2               this_class;
 * u2               super_class;
 * u2               interfaces_count;
 * un               interfaces[interfaces_count]
 * u2               fields_count;
 * field_info       fields[fields_count];
 * u2               methods_count;
 * method_info      methods[methods_count];
 * u2               attributes_count;
 * attribute_info   attributes[attributes_count];
 * }
 * Java字节码结构
 * ·Class字节码中有两种数据类型
 * ··字节数据直接量:这是基本的数据类型.共细分为u1、u2、u4、u8四种,分别代表连续的1个字节、2个字节、4个字节、8个字节组成的整体
 * 数据.
 * ··表(数组):表是由多个基本数据或其他表,按照既定顺序组成的大的数据集合.表是由结构的,它的结构体现在:组成表的成分所在的位置和
 * 顺序都是已经严格定义好的.
 * <p>
 * Access_Flag访问标志
 * 访问标志信息包括该Class文件是类还是接口,是否被定义成public,是否是abstract,如果是类,是否被声明称final.
 * 例如:0x0021是0x0020和0x0001的并集,表示ACC_PUBLIC与ACC_SUPER.
 * 字段表集合:字段表用于描述类和接口中表明的变量.这里的字段包含了类级别变量以及实例变量,但是不包括方法内部声明的局部变量.
 * field_info{
 * 类型                       名称                             数量
 * u2                     access_flags(访问修饰符)               1
 * u2                     name_index(名字索引)                   1
 * u2                     descriptor_index(描述符[类型]索引)     1
 * u2                     attributes_count                      1
 * attribute_info         attributes                      attributes_count
 * }
 * <p>
 * 42.Java字节码方法表与属性表深度剖析
 * 方法表:
 * methods_count{
 * 类型                       名称                             数量
 * u2                     access_flags(访问修饰符)               1
 * u2                     name_index(名字索引)                   1
 * u2                     descriptor_index(描述符[类型]索引)     1
 * u2                     attributes_count                      1
 * attribute_info         attributes                      attributes_count
 * }
 * 方法的属性结构:
 * 方法中的每个属性都是一个attribute_info结构
 * attribute_info{
 * 类型                       名称                             数量
 * u2                 attribute_name_index                      1
 * u4                 attribute_length                          1
 * u1                 info[attribute_length]
 * }
 * ·JVM预定义了部分attribute,但是编译器自己也可以实现自己的attribute写入class文件里,供运行时使用.
 * ·不同的attribute通过attribute_name_index来区分
 * Code结构:
 * Code attribute的作用是保存该方法的结构,如所对应的字节码
 * Code_attribute{
 * u2   attribute_name_index;
 * u4   attribute_length;
 * u2   max_stack;
 * u2   max_locals;
 * u4   code_length;
 * u1   code[code_length];
 * u2   exception_table_length;
 * `  {
 * `     u2  start_pc;
 * `     u2  end_pc;
 * `     u2  handler_pc;
 * `     u2  catch_type;
 * `  } exception_table[exception_table_length];
 * u2   attribute_count;
 * attribute_info attributes[attributes_count];
 * }
 */
public class MyTest1 {

    private int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}