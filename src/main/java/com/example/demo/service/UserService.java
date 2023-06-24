package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.Perms;
import com.example.demo.pojo.User;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService extends IService<User> {
    //注册用户方法
    void register(User user);

    User findByUserName(String name);

    List<User> findAllUsers();

    int saveUser(User user);

    User findByUserId(Integer id);


    User findRolesByUserName(String username);
    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);

    void resetPW(User user);

    @Async
    public void sendVer(String verCode, String userMail) throws MessagingException;
}
