package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.Provider;
import com.example.demo.pojo.User;

import java.util.List;

public interface ProviderService extends IService<Provider>  {
    Provider findByProviderName(String name);

    List<Provider> findAllProvider();

    int saveProvider(User user);

    Provider findByProviderId(Integer id);

    List<Provider> findLikeProvider(String proCode, String proName);
}
