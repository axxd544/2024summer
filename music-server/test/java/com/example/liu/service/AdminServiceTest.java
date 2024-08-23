package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;
import com.example.liu.mapper.AdminMapper;
import com.example.liu.model.request.AdminRequest;
import com.example.liu.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    @Mock
    private AdminMapper adminMapper;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVerityPasswd_Success() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUsername("admin");
        adminRequest.setPassword("password");

        when(adminMapper.selectCount(any(QueryWrapper.class))).thenReturn(1L); // Adjusted to Long

        R expectedResponse = R.success("登录成功");
        R actualResponse = adminService.verityPasswd(adminRequest, session);

        assertEquals(expectedResponse, actualResponse);
        verify(session, times(1)).setAttribute("name", adminRequest.getUsername());
        verify(adminMapper, times(1)).selectCount(any(QueryWrapper.class));
    }

    @Test
    public void testVerityPasswd_Failure() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUsername("admin");
        adminRequest.setPassword("wrongpassword");

        when(adminMapper.selectCount(any(QueryWrapper.class))).thenReturn(0L); // Adjusted to Long

        R expectedResponse = R.error("用户名或密码错误");
        R actualResponse = adminService.verityPasswd(adminRequest, session);

        assertEquals(expectedResponse, actualResponse);
        verify(session, never()).setAttribute(anyString(), anyString());
        verify(adminMapper, times(1)).selectCount(any(QueryWrapper.class));
    }
}
