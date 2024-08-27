package com.example.consumerservice.controller;

import com.example.consumerservice.common.R;
import com.example.consumerservice.model.request.AdminRequest;
import com.example.consumerservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 后台管理的相关事宜
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    // 判断是否登录成功
    @PostMapping("/admin/login/status")
    public R loginStatus(@RequestBody AdminRequest adminRequest, HttpSession session) {
        return adminService.verityPasswd(adminRequest, session);
    }
}
