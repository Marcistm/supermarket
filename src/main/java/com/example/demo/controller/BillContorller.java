package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Bill;
import com.example.demo.pojo.Provider;
import com.example.demo.service.BillService;
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
import java.util.List;

@RequestMapping("/bill")
@Controller
public class BillContorller {
    @Autowired
    BillService billService;


    @RequestMapping("/list")
    public String toListHtml(){
        return "bill/list";
    }
    @GetMapping("/list")
    public String showUsers(Model model,
                            @RequestParam(required=false,defaultValue="1") Integer pageNow,
                            @RequestParam(required=false,defaultValue="4") Integer pageSize){
        PageHelper.startPage(pageNow,pageSize);
        List<Bill> bills = billService.findAllBill();
        PageInfo<Bill> pageInfo = new PageInfo<Bill>(bills);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("bills", bills);
        System.out.println("查询结果---》" + bills);
        return "bill/list";
    }
//
//
    @PutMapping("/bill")
    public String updateOrSaveProvider(Bill bill){
        //更新订单信息或是添加一个订单
        billService.saveOrUpdate(bill);
        return "redirect:/bill/list";
    }


    @GetMapping("/bill/{id}")
    public void findUserByid(@PathVariable("id") Integer id, HttpServletResponse response){
        try {
            System.out.println("收到请求=============");
            PrintWriter out = response.getWriter();
            Bill bill = billService.getById(id);
            String json = JSON.toJSONString(bill);
            System.out.println("JSON=============" + json);

            out.write(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//
    @DeleteMapping("/bill/{id}")
    public String deletUser(@PathVariable("id") Integer id){
        System.out.println("删除的订单id："+ id);
        billService.removeById(id);
        return "redirect:/bill/list";
    }
//
//
//
    @RequestMapping("/find")
    public String findById(@RequestParam("billCode") String billCode, Model model,
                           @RequestParam("productName") String productName,
                           @RequestParam(required=false,defaultValue="1") Integer pageNow,
                           @RequestParam(required=false,defaultValue="4") Integer pageSize){
        PageHelper.startPage(pageNow,pageSize);
        List<Bill> bills = billService.findLikeBill(billCode, productName);
        PageInfo<Bill> pageInfo = new PageInfo<Bill>(bills);
        model.addAttribute("bills", bills);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("billCode", billCode);
        model.addAttribute("productName", productName);
        return "/bill/list";
    }
}
