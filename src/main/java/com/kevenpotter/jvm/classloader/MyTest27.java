/**
 * 31.通过JDBC驱动加载深刻理解线程上下文类加载器机制
 */
package com.kevenpotter.jvm.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyTest27 {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb", "username", "password");
    }
}
