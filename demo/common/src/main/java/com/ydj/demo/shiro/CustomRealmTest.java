package com.ydj.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 测试自定义Realm
 *
 * @author yidujun
 * @date 2020/4/15 20:03
 */
public class CustomRealmTest {

    @Test
    public void tetAutentication() {
        CustomRealm customRealm = new CustomRealm();

        /**
         *  1.构建Security Manager环境
         *  （Security Manager是用来提供安全服务的，所以在做shiro认证的时候要先创建此对象,创建Security Manager对象之后要设置Realm）
         */
        //使用SecurityUtils之前要设置Security Manager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);


        /**
         * 2.获取向Security Manager提交请求的subject
         *
         */
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        /**
         * 3.主体Subject提交请求给Security Manager
         */
        UsernamePasswordToken token = new UsernamePasswordToken("ydjws", "123456");
        subject.login(token);

        /**
         * 4.shiro提供一个检查主体subject是否认证的方法isAuthenticated()
         * 此方法的返回结果是一个boolean值
         */
        System.out.println(subject.isAuthenticated());

        subject.checkRoles("admin");
        subject.checkPermission("user:delete");
    }
}
