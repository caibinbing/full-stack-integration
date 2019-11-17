package main.java.multithread.chapter6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    枚举和静态代码块的特性相似，在使用枚举类时，构造方法会被自动调用
    注意：完善“责任单一原则”
 */
public class RunEnumSingleton {
    public static void main(String[] args) {
        EnumThread t1 = new EnumThread();
        EnumThread t2 = new EnumThread();
        EnumThread t3 = new EnumThread();
        t1.start();
        t2.start();
        t3.start();
    }
}

class EnumObj {
    enum EnumObjSingleton {
        connectionFactory,
        ;
        private Connection connection;

        EnumObjSingleton() {
            try {
                System.out.println("调用了EnumObj的构造");
                String url = "jdbc:sqlserver://localhost:1079;databaseName=ghydb";
                String username = "sa";
                String password = "";
                String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                Class.forName(driverName);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        Connection getConnection() {
            return connection;
        }
    }

    static Connection getConnection() {
        return EnumObjSingleton.connectionFactory.getConnection();
    }
}

class EnumThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(EnumObj.getConnection().hashCode());
        }
    }
}