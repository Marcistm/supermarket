package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Message;
import com.example.demo.utils.SentSimpleMail;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public String login(User user, Model model, HttpSession session) {
        System.out.println("登录时====》" + user.toString());
        try {
            //获取主体对象
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(user.getName(), user.getPassword()));
            session.setAttribute("user", user);
            //return "redirect:/index";
            return "redirect:/main.html";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
            model.addAttribute("msg", "用户名或密码错误!");
            return "index";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
            model.addAttribute("msg", "用户名或密码错误!");
            return "index";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "index";
        }

    }

    @RequestMapping(value = "/register")
    public String register(User user) {
        try {
            userService.register(user);
            System.out.println("用户注册成功...");
            return "redirect:/index.html";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register";
        }
    }

    @GetMapping("/list")
    public String showUsers(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer pageNow,
                            @RequestParam(required = false, defaultValue = "4") Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<User> users = userService.findAllUsers();
        PageInfo<User> pageInfo = new PageInfo<User>(users);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("users", users);
        return "user/list";
    }


    @PostMapping("/user")
    public String addUser(User user) {
        System.out.println("待插入用户====》" + user);
        int insert = userService.saveUser(user);
        System.out.println("插入用户条数===》" + insert);
        return "redirect:/user/list";
    }

    @GetMapping("/user/{id}")
    public void findUserByid(@PathVariable("id") Integer id, HttpServletResponse response) {
        try {
            System.out.println("收到请求=============");
            PrintWriter out = response.getWriter();
            User user = userService.findByUserId(id);
            String json = JSON.toJSONString(user);
            System.out.println("JSON=============" + json);

            out.write(json);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        //更新用户信息
        userService.saveOrUpdate(user);
        return "redirect:/user/list";
    }

    @PutMapping("/user")
    public String updateUser1(User user) {
        //更新用户信息
        userService.saveOrUpdate(user);
        return "redirect:/user/list";
    }

    @DeleteMapping("/user/{id}")
    public String deletUser(@PathVariable("id") Integer id) {
        System.out.println("删除的员工id：" + id);
        userService.removeById(id);
        return "redirect:/user/list";
    }


    @RequestMapping("/find")
    public String findById(@RequestParam("id") Integer id, Model model,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNow,
                           @RequestParam(required = false, defaultValue = "4") Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<User> users = userService.listByMap(map);
        PageInfo<User> pageInfo = new PageInfo<User>(users);

        model.addAttribute("users", users);
        model.addAttribute("pageInfo", pageInfo);
        return "/user/list";
    }

    @RequestMapping("/signOut")
    public String signOut(HttpSession session) {
        session.removeAttribute("user");
        return "index";
    }


    @RequestMapping("/resetPW/{name}")
    public String resetPW(@PathVariable("name") String name, Model model) {
        User user = userService.findByUserName(name);
        model.addAttribute("user", user);
        return "user/resetPW";
    }

    @RequestMapping(value = "/resetPW")
    public String resetPW(User user) {
        try {
            System.out.println("用户修改成功...");
            userService.resetPW(user);
            return "redirect:/index.html";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/findPW")
    public String findPwd() {
        return "user/findPW";
    }

//    @RequestMapping(value = "/userSendVer")
//    public void findPwd(@RequestParam("userMail") String userMail, HttpServletResponse response){
//        System.out.println("进入发送验证码函数=====");
//        String verCode = String.valueOf(new Random().nextInt(100000));
//        try {
//            new SentSimpleMail().sentSimpleMail("找回密码", verCode, userMail);
//            //编码设置
//            Message message = new Message();
//            message.setCode(0);
//            message.setMsg("发送成功");
//            message.setCount(1);
//            message.setData(verCode); //
//
//            String content = JSON.toJSONString(message);
//
//            PrintWriter out = response.getWriter();
//            out.write(content);
//        } catch (MessagingException | IOException e) {
//            e.printStackTrace();
//        }
//    }


    @RequestMapping(value = "/userSendVer")
    public void findPwd(@RequestParam("userMail") String userMail, HttpServletResponse response) {
        System.out.println("进入发送验证码函数=====");
        String verCode = String.valueOf(new Random().nextInt(100000));
//            new SentSimpleMail().sentSimpleMail("找回密码", verCode, userMail);
        try {
            userService.sendVer(verCode, userMail);
            Message message = new Message();
            message.setCode(0);
            message.setMsg("发送成功");
            message.setCount(1);
            message.setData(verCode); //
            String content = JSON.toJSONString(message);
            PrintWriter out = null;
            out = response.getWriter();
            out.write(content);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

    }


}
