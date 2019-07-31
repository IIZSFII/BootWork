package com.lottery.shiro;

import com.lottery.entity.User;
import com.lottery.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    /*执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       System.out.println("执行授权逻辑");
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
       User userinfo=userRepository.findByUserName(user.getUserName());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //
        return null;
    }

    /*执行认证逻辑*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        //逻辑获取用户名，密码
        User user=userRepository.findByUserName(token.getUsername());
        if(null==user){
            return null;
        }
        String paswword=user.getPassword();
        return new SimpleAuthenticationInfo(user,paswword,"");
    }
}
