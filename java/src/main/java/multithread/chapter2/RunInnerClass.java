package main.java.multithread.chapter2;

public class RunInnerClass {
    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        publicClass.setUsername("usernameValue");
        publicClass.setPassword("passwordValue");
        System.out.println(publicClass.getUsername() + " " + publicClass.getPassword());
        //注意内置类初始化的方法
        PublicClass.PrivateClass privateClass = publicClass.new PrivateClass();
        privateClass.setAddress("addressValue");
        privateClass.setAge("AgeValue");
        System.out.println(privateClass.getAddress() + " " + privateClass.getAge());
    }
}

//如果PublicClass.java和Run.java不在同一个包中，则需要将PrivateClass内置声明成public公开的
class PublicClass {
    private String username;
    private String password;

    class PrivateClass {
        private String age;
        private String address;

        String getAge() {
            return age;
        }

        void setAge(String age) {
            this.age = age;
        }

        String getAddress() {
            return address;
        }

        void setAddress(String address) {
            this.address = address;
        }

        public void printPublicProperty() {
            System.out.println(username + " " + password);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }
}