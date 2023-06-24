package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.ProviderMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.Provider;
import com.example.demo.pojo.User;
import com.example.demo.service.ProviderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {
    @Autowired
    ProviderMapper providerMapper;
    @Override
    public Provider findByProviderName(String name) {
        return null;
    }

    @Override
    public List<Provider> findAllProvider() {
        List<Provider> providers = providerMapper.selectList(null);
        return providers;
    }

    @Override
    public int saveProvider(User user) {
        return 0;
    }

    @Override
    public Provider findByProviderId(Integer id) {
        return providerMapper.selectById(id);
    }

    @Override
    public List<Provider> findLikeProvider(String proCode, String proName) {
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(proCode), "proCode", proCode);
        queryWrapper.like(StringUtils.isNotBlank(proName), "proName", proName);
        List<Provider> providers = providerMapper.selectList(queryWrapper);
        return  providers;
    }
}
