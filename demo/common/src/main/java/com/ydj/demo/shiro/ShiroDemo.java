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
        simpleAccountRealm.addAccount("ydj", "123456", "admin");
    }

    @Test
    public void shiroTest() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("ydj", "123456");
        subject.login(token);
        System.out.println("是否登录：" + subject.isAuthenticated());
        subject.checkRoles("admin");

        // 退出登录
        subject.logout();
        System.out.println("是否登录：" + subject.isAuthenticated());



    }
}
