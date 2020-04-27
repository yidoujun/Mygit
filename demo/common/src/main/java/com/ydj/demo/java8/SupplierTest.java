package com.ydj.demo.java8;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * Supplier测试
 *
 * @author yidujun
 * @date 2020/4/26 20；15
 */
public class SupplierTest {

    @Test
    public void test(){
        Supplier<User> supplier = () -> new User();
        System.out.println(supplier);
        User user = supplier.get();
        System.out.println(user);
        System.out.println(supplier.get());
    }
    // 内部类
    private class User {
        private Integer id;
        private String userName;
        private String pwd;

        User() {}

        User(Integer id, String userName, String pwd){
            this.id = id;
            this.userName = userName;
            this.pwd = pwd;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getId() {
            return this.id;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getUserName() {
            return this.userName;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
        public String getPwd() {
            return this.pwd;
        }

        /*@Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", pwd='" + pwd + '\'' +
                    '}';
        }*/
    }
}
