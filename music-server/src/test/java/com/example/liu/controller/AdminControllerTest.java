package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.AdminRequest;
import com.example.liu.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginStatus_Success() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUsername("admin");
        adminRequest.setPassword("password");

        MockHttpSession session = new MockHttpSession();

        R expectedResponse = R.success("Login successful");
        when(adminService.verityPasswd(any(AdminRequest.class), any(HttpSession.class)))
                .thenReturn(expectedResponse);

        R response = adminController.loginStatus(adminRequest, session);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testLoginStatus_Failure() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setUsername("admin");
        adminRequest.setPassword("wrongpassword");

        MockHttpSession session = new MockHttpSession();

        R expectedResponse = R.error("Login failed");
        when(adminService.verityPasswd(any(AdminRequest.class), any(HttpSession.class)))
                .thenReturn(expectedResponse);

        R response = adminController.loginStatus(adminRequest, session);

        assertThat(response).isEqualTo(expectedResponse);
    }
}
