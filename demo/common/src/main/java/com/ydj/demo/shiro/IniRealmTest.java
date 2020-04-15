package com.ydj.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * shiro---自定义realm
 *
 * @author yidujun
 * @date 2020/4/15 19:02
 */
public class IniRealmTest {

    @Test
    public void testAutentication() {
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        /**
         * 1.构建Security Manager环境
         * Security Manager是用来提供安全服务的，所以在做shiro认证的时候要先创建此对象,创建Security Manager对象之后要设置Realm
         */
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        /**
         * 3.获取ecurity Manager提交请求的subject，而主体subject通过一个工具类
         */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("ydjws","123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        subject.checkRoles("admin");
        subject.checkPermission("user:delete");
    }
}
