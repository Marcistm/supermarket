package com.example.demo.realm;

import com.example.demo.pojo.Perms;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerDBRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("授权什么时候调用。。。。。。。。。。。");
        //获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证: " + primaryPrincipal);
        User user = userService.findRolesByUserName(primaryPrincipal);
        System.out.println("user:" + user);
        //授权角色信息
        if (!CollectionUtils.isEmpty(user.getRoles())) {

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName()); //添加角色信息

                //权限信息
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                System.out.println("perms:" + perms);

                if (!CollectionUtils.isEmpty(perms) && perms.get(0) != null) {
                    perms.forEach(perm -> {
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
        System.out.println("================================");
        //根据身份信息//从传过来的token获取到的用户名
        String principal = (String) token.getPrincipal();
        System.out.println(principal);
        //根据身份信息查询
        User user = userService.findByUserName(principal);
        System.out.println("User:" + user);

        //用户不为空
        if (!ObjectUtils.isEmpty(user)) {
            //返回数据库信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    user.getName(),
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
