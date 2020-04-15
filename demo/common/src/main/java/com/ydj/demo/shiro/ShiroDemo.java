package com.ydj.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * shiro权限demo
 *
 * shiro认证过程
 * 创建SecurityManager-->主体提交认证-->SecurityManager认证-->Authenticsto认证-->Realm验证
 * </p>
 * Shiro授权过程
 * 创建SecurityManager-->主体授权-->SecurityManager授权-->Authorize授权-->Realm获取角色权限数据
 * </p>
 *
 * @author yidujun
 * @date 2020/4/15 16:08
 */
public class ShiroDemo {

    // 除了SimpleAccountRealm还有bcRealm等
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        // 添加用户的时候为此用户添加角色，一个用户可以拥有一个或者多个角色
        simpleAccountRealm.addAccount("ydj", "123456", "admin");
    }

    @Test
    public void shiroTest() {
        /**
         * 1.构建Security Manager环境
         * Security Manager是用来提供安全服务，所以在做shiro认证的时候要先创建此对象
         * 创建Security Manager对象之后要设置Realm
         */
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        /**
         * 2.获取向Security Manager提交请求的subject
         * 而主体subject可以通过shiro提供的一个工具类SecurityUtiles来获取
         */
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        /**
         * 3.主体subject提交请求给Security Manager  -- > subject.login(token)
         */
        //提交请求时需要一个token，所以先创建token
        UsernamePasswordToken token = new UsernamePasswordToken("ydj", "123456");
        subject.login(token);
        /**
         * 4.shiro提供一个检查主体subject是否认证的方法isAuthenticated(),
         *  此方法的返回结果是一个boolean值
         */
        System.out.println("是否登录：" + subject.isAuthenticated());
        // 校验角色
        subject.checkRoles("admin");

        // 退出登录
        subject.logout();
        System.out.println("是否登录：" + subject.isAuthenticated());
    }
}
