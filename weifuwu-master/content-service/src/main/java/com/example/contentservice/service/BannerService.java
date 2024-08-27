package com.example.contentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.contentservice.model.domain.Banner;

import java.util.List;

/**
* @author asus
* @description 针对表【banner】的数据库操作Service
*/
public interface BannerService extends IService<Banner> {

    List<Banner> getAllBanner();

}
