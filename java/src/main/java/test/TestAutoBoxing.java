package main.java.test;

public class TestAutoBoxing {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d); //true 缓存了，同一个对象
        // 产生这样的结果的原因是：Byte、Short、Integer、Long、Char这几个装箱类的valueOf()方法是以128位分界线做了缓存的，
        // 假如是128以下且-128以上的值是会取缓存里面的引用的，以Integer为例，其valueOf(int i)的源代码为：
        System.out.println(e == f); //false
        System.out.println(c == (a + b));   //true
        System.out.println(c.equals(a + b));    //true
        System.out.println(g == (a + b));   //true
        System.out.println(g.equals(a + b));    //false
    }
}
