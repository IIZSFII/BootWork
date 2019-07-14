package com.lottery.controller;

import com.lottery.dao.CustomerRepository;
import com.lottery.entity.Customer;
import com.lottery.util.Scheduled.AsyncSchedulerDemo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.concurrent.Future;

@Controller
public class FirstController {
    Logger Logger=LoggerFactory.getLogger(FirstController.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AsyncSchedulerDemo instance;

    @ResponseBody
    @RequestMapping("/main")
    public Customer findSom(){
        Customer lc= customerRepository.findByCustNameAndCustAddressEquals("企鹅答答","全球");
        Logger.info("记录日志");
        Logger.debug("记录日志BUG");
        return lc;
    }

    @Scheduled(fixedRate = 1000)
    public void doTime(){
        Date date = new Date();
        System.out.println("ZSF"+date);
    }

    @ResponseBody
    @RequestMapping("/mains")
    public String test() {
        long startTime = System.currentTimeMillis();
        Future<String> future1 = instance.task1();
        Future<String> future2 = instance.task2();
        Future<String> future3 = instance.task3();
        for (; ;) {
            if (future1.isDone() && future2.isDone() && future3.isDone()) {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("异步任务总耗时：" + (endTime - startTime));
        return "异步任务总耗时：" + (endTime - startTime) ;
    }
    @RequestMapping("/index1")
    public String index(Model model){
        Customer lc= customerRepository.findByCustNameAndCustAddressEquals("企鹅答答","全球");
        model.addAttribute("name",lc.getCustName());
        return "index";
    }
    @RequestMapping("/add")
    public String addUser(Model model){
        return "/user/add";
    }
    @RequestMapping("/update")
    public String updateUser(Model model){
        return "/user/update";
    }
    @RequestMapping("/tologin")
    public String tologin(Model model){
        return "/login";
    }

    @RequestMapping("/unAuth")
    public String unAuth(Model model){
        return "/unAuth";
    }

    @RequestMapping("/login")
    public String login(String name,String password, Model model){
        //设置shiro认证
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken UsernamePasswordToken=new UsernamePasswordToken(name,password);
        try {
            subject.login(UsernamePasswordToken);
            //登录成功
            return "redirect:/index1";
        }catch (UnknownAccountException e){
            //e.printStackTrace();
           //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
    @RequestMapping("/loginOut")
    public String logOut(Model model){
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return  "redirect:/tologin";
    }
}
