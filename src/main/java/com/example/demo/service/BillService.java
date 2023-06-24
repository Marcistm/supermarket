package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.Bill;
import com.example.demo.pojo.Provider;
import com.example.demo.pojo.User;

import java.util.List;

public interface BillService extends IService<Bill>  {
    Bill findByBillName(String name);

    List<Bill> findAllBill();

    int saveBill(User user);

    Bill findByBillId(Integer id);

    List<Bill> findLikeBill(String billCode, String productName);
}
