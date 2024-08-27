package com.example.consumerservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.consumerservice.common.R;
import com.example.consumerservice.model.domain.Admin;
import com.example.consumerservice.model.request.AdminRequest;

import javax.servlet.http.HttpSession;

public interface AdminService extends IService<Admin> {

    R verityPasswd(AdminRequest adminRequest, HttpSession session);
}
