package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.Perms;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.SaltUtils;
import com.example.demo.utils.SentSimpleMail;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;


    public void register(User user) {
        //处理业务调用dao
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userMapper.insert(user);
    }

    @Override
    public User findByUserName(String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<User> users = userMapper.selectByMap(map);
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @Override
    public int saveUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public User findByUserId(Integer id) {
        return userMapper.selectById(new Long(id));
    }

    @Override
    public User findRolesByUserName(String username) {
        return userMapper.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(String id) {
        return userMapper.findPermsByRoleId(id);
    }

    @Override
    public void resetPW(User user) {
        //处理业务调用dao
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        this.saveOrUpdate(user);
    }


    @Async
    @Override
    public void sendVer(String verCode, String userMail) throws MessagingException {
        System.out.println("进入发送验证码函数=====");
        new SentSimpleMail().sentSimpleMail("找回密码", verCode, userMail);

    }
}
