package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.Provider;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.service.ProviderService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.parser.Entity;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    ProviderService providerService;
    @Test
    void contextLoads() {
    }


    @Test
    void test1(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        System.out.println(("----- selectAll method test ------"));
        User user = new User();
        user.setName("test3");
        user.setPassword("123");

        userMapper.insert(user);
    }

    @Test
    void test2(){
        List<User> users = userService.list(null);
        users.forEach(System.out::println);

//        User user = new User();
//        user.setName("test0");
//        int insert = userMapper.insert(user);
//        System.out.println(insert);

//        User user = userMapper.selectById(new Long(100));
//        System.out.println(user);
        List<Provider> allProvider = providerService.findAllProvider();
        allProvider.forEach(System.out::println);

    }
    @Test
    void test3(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "test3");
        BaseMapper<User> baseMapper = userService.getBaseMapper();
        List<User> users = baseMapper.selectByMap(map);
        users.forEach(System.out::println);
    }


    @Test
    void test4(){
        //条件封装
//        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(StringUtils.isNotBlank(""), "proCode", "");
//        queryWrapper.like(StringUtils.isNotBlank(""), "proName", "");
//        List<Provider> providers = providerService.getBaseMapper().selectList(queryWrapper);
//        System.out.println(providers);
        User user = userService.findByUserName("admin");
        System.out.println(user);
        List<Role> roles = user.getRoles();
        roles.forEach(System.out :: println);


    }



}
