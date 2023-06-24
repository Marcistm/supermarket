package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Provider;
import com.example.demo.pojo.User;
import com.example.demo.service.ProviderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/provider")
@Controller
public class ProviderContorller {
    @Autowired
    ProviderService providerService;


    @RequestMapping("/list")
    public String toListHtml(){
        return "provider/list";
    }
    @GetMapping("/list")
    public String showUsers(Model model,
                            @RequestParam(required=false,defaultValue="1") Integer pageNow,
                            @RequestParam(required=false,defaultValue="4") Integer pageSize){
        PageHelper.startPage(pageNow,pageSize);
        List<Provider> providers = providerService.findAllProvider();
        PageInfo<Provider> pageInfo = new PageInfo<Provider>(providers);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("providers", providers);
        System.out.println("查询结果---》" + providers);
        return "provider/list";
    }


    @PutMapping("/provider")
    public String updateOrSaveProvider(Provider provider){
        //更新用户信息
        providerService.saveOrUpdate(provider);
        return "redirect:/provider/list";
    }


    @GetMapping("/provider/{id}")
    public void findUserByid(@PathVariable("id") Integer id, HttpServletResponse response){
        try {
            System.out.println("收到请求=============");
            PrintWriter out = response.getWriter();
            Provider provider = providerService.findByProviderId(id);
            String json = JSON.toJSONString(provider);
            System.out.println("JSON=============" + json);

            out.write(json);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @DeleteMapping("/provider/{id}")
    public String deletUser(@PathVariable("id") Integer id){
        System.out.println("删除的员工id："+ id);
        providerService.removeById(id);
        return "redirect:/provider/list";
    }



    @RequestMapping("/find")
    public String findById(@RequestParam("proCode") String proCode, Model model,
                           @RequestParam("proName") String proName,
                           @RequestParam(required=false,defaultValue="1") Integer pageNow,
                           @RequestParam(required=false,defaultValue="4") Integer pageSize){
        PageHelper.startPage(pageNow,pageSize);
        List<Provider> providers = providerService.findLikeProvider(proCode, proName);
        PageInfo<Provider> pageInfo = new PageInfo<Provider>(providers);
        model.addAttribute("providers", providers);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("proCode", proCode);
        model.addAttribute("proName", proName);

        return "/provider/list";
    }
}
