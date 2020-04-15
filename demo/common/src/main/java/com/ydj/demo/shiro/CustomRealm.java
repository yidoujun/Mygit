package com.ydj.demo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义realm
 *
 * @author yidujun
 * @date 2020/4/15 19:23
 */
public class CustomRealm extends AuthorizingRealm {

    // 简单定义一个缓存
    Map<String, String> userMap = new HashMap<String, String>(16);
    // 构造方法
    {
        userMap.put("ydjws", "123456");
        super.setName("customReal");    //自定义
    }
    /**
     * 用来做授权(就是checkRole，checkPermission时用到的)
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.先获取用户名
        String userName = (String) principals.getPrimaryPrincipal();
        //2.从缓存或者数据库获取角色数据
        Set<String> roles = getRolesByUserName(userName);
        //3.从数据库或缓存中获取权限数据
        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 用来做认证(就是login时用到的)
     * @param token     主体subject传过来的验证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.先通过主体传过来的验证信息获取用户名
        String userName = (String) token.getPrincipal();
        //2.通过用户名去数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if (password == null) {
            return null;
        }
        // 查询到用户，则返回AuthenticationInfo对象
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, this.getName());
        return authenticationInfo;
    }

    /**
     * 通过用户名获取角色数据
     * @param userName
     * @return
     */
    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * 通过用户名获取权限数据
     * @param userName
     * @return
     */
    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    private String getPasswordByUserName(String userName) {
        // 模拟查询数据库
        return userMap.get(userName);
    }
}

