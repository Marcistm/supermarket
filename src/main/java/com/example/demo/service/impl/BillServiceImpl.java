package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BillMapper;
import com.example.demo.dao.ProviderMapper;
import com.example.demo.pojo.Bill;
import com.example.demo.pojo.Provider;
import com.example.demo.pojo.User;
import com.example.demo.service.BillService;
import com.example.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
    @Autowired
    BillMapper billMapper;

    @Override
    public Bill findByBillName(String name) {
        return null;
    }

    @Override
    public List<Bill> findAllBill() {
        List<Bill> bills = billMapper.selectList(null);
        return bills;
    }

    @Override
    public int saveBill(User user) {
        return 0;
    }

    @Override
    public Bill findByBillId(Integer id) {
        return null;
    }

    @Override
    public List<Bill> findLikeBill(String billCode, String productName) {

        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(billCode), "billCode", billCode);
        queryWrapper.like(StringUtils.isNotBlank(productName), "productName", productName);
        List<Bill> bills = billMapper.selectList(queryWrapper);
        return  bills;

    }
}
