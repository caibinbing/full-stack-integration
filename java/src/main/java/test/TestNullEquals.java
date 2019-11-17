package main.java.test;

public class TestNullEquals {

    public static void main(String[] args) {
//        String nullStr = null;    //NullPointerException
        String nullStr = "";    //always false
        System.out.println(nullStr.equals(null)); //NullPointerException
    }
}
