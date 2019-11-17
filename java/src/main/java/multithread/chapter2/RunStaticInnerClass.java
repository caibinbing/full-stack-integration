package main.java.multithread.chapter2;

public class RunStaticInnerClass {
    public static void main(String[] args) {
        PublicClass2 publicClass2 = new PublicClass2();
        publicClass2.setUsername("usernameValue");
        publicClass2.setPassword("passwordValue");
        System.out.println(publicClass2.getUsername() + " " + publicClass2.getPassword());
        //注意静态内置类使用方法
        PublicClass2.PrivateClass2 privateClass2 = new PublicClass2.PrivateClass2();
        privateClass2.setAddress("addressValue");
        privateClass2.setAge("ageValue");
        System.out.println(privateClass2.getAge() + " " + privateClass2.getAddress());
    }
}

class PublicClass2 {
    private static String username;
    private static String password;

    static class PrivateClass2 {
        private String age;
        private String address;

        public String getAge() {
            return age;
        }

        void setAge(String age) {
            this.age = age;
        }

        String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void printPublicProperty() {
            System.out.println(username + " " + password);
        }
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        PublicClass2.username = username;
    }

    static String getPassword() {
        return password;
    }

    static void setPassword(String password) {
        PublicClass2.password = password;
    }
}