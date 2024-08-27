package com.example.consumerservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.consumerservice.common.R;
import com.example.consumerservice.mapper.AdminMapper;
import com.example.consumerservice.model.domain.Admin;
import com.example.consumerservice.model.request.AdminRequest;
import com.example.consumerservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public R verityPasswd(AdminRequest adminRequest, HttpSession session) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",adminRequest.getUsername());
        queryWrapper.eq("password",adminRequest.getPassword());
        if (adminMapper.selectCount(queryWrapper) > 0) {
            session.setAttribute("name", adminRequest.getUsername());
            return R.success("登录成功");
        } else {
            return R.error("用户名或密码错误");
        }
    }
}
