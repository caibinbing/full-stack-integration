package main.java.test;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestThreadLocal {
    private static ThreadLocal<Connection> connectionHolder = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    });

    public static Connection getConnection() {
        return connectionHolder.get();
    }

    public static void main(String[] args) {


    }

}

interface Computable<A, V>{
    V compute(A arg);
}

class ExpensiveFunction implements Computable<String, BigInteger> {

    @Override
    public BigInteger compute(String arg) { //参数需要和泛型相同。
        return null;
    }
}