package main.java.multithread.chapter1;

public class RunLoginServlet {
    public static void main(String[] args) {
        /**
         * Error
         * username = b password = bb
         * username = b password = aa
         */
        ALogin aLogin = new ALogin();
        aLogin.start();
        BLogin bLogin = new BLogin();
        bLogin.start();
    }
}

class ALogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("a", "aa");
    }
}

class BLogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("b", "bb");
    }
}

class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;

    /**
     * 1、可将全局变量变为局部变量
     * 2、加synchronized
     */
    synchronized static void doPost(String username, String password) {
        usernameRef = username;
        if ("a".equals(username)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        passwordRef = password;
        System.out.println("username = " + usernameRef + " password = " + password);
    }
}
