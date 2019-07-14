package com.lottery.shiro;

import com.lottery.dao.CustomerRepository;
import com.lottery.entity.Customer;
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
    private CustomerRepository customerRepository;

    /*执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       System.out.println("执行授权逻辑");
        Subject subject = SecurityUtils.getSubject();
        Customer customer=(Customer) subject.getPrincipal();
        Customer customerInfo=customerRepository.findByCustName(customer.getCustName());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        System.out.println(customerInfo.getCustSource());
        info.addStringPermission(customerInfo.getCustSource());
        return info;
    }

    /*执行认证逻辑*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        //逻辑获取用户名，密码
        Customer customer=customerRepository.findByCustName(token.getUsername());
        String name=customer.getCustName(); String paswword=customer.getCustLevel();
        if(null==customer){
        return null;
        }
        return new SimpleAuthenticationInfo(customer,paswword,"");
    }
}
