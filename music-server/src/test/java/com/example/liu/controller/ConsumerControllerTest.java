package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.ConsumerRequest;
import com.example.liu.service.ConsumerService;
import com.example.liu.service.impl.ConsumerServiceImpl;
import com.example.liu.service.impl.SimpleOrderManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConsumerControllerTest {

    @InjectMocks
    private ConsumerController consumerController;

    @Mock
    private ConsumerService consumerService;

    @Mock
    private ConsumerServiceImpl consumerServiceImpl;

    @Mock
    private SimpleOrderManager simpleOrderManager;

    @Mock
    private StringRedisTemplate stringRedisTemplate;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        ConsumerRequest request = new ConsumerRequest();
        // Mocking the service call
        when(consumerService.addUser(request)).thenReturn(R.success("用户注册成功"));

        R response = consumerController.addUser(request);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("用户注册成功");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testLoginStatus() {
        ConsumerRequest request = new ConsumerRequest();
        // Mocking the service call
        when(consumerService.loginStatus(request, session)).thenReturn(R.success("登录成功"));

        R response = consumerController.loginStatus(request, session);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("登录成功");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testLoginEmailStatus() {
        ConsumerRequest request = new ConsumerRequest();
        // Mocking the service call
        when(consumerService.loginEmailStatus(request, session)).thenReturn(R.success("Email 登录成功"));

        R response = consumerController.loginEmailStatus(request, session);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Email 登录成功");
        assertThat(response.getSuccess()).isTrue();
    }
  
    
    @Test
    public void testSendCode_UserNotExist() {
        String email = "test@example.com";
        when(consumerService.findByEmail(email)).thenReturn(null);

        R response = consumerController.sendCode(email);

        assertThat(response.getCode()).isEqualTo(500);
        assertThat(response.getMessage()).isEqualTo("用户不存在");
        assertThat(response.getSuccess()).isFalse();
    }

    @Test
    public void testAllUser() {
        // Mocking the service call
        when(consumerService.allUser()).thenReturn(R.success("所有用户"));

        R response = consumerController.allUser();

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("所有用户");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testUserOfId() {
        int id = 1;
        // Mocking the service call
        when(consumerService.userOfId(id)).thenReturn(R.success("用户详情"));

        R response = consumerController.userOfId(id);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("用户详情");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testUserLikeUsername() {
        String username = "test";
        // Mocking the service call
        when(consumerService.likeUsername('%' + username + '%')).thenReturn(R.success("匹配用户"));

        R response = consumerController.userLikeUsername(username);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("匹配用户");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testDeleteUser() {
        int id = 1;
        // Mocking the service call
        when(consumerService.deleteUser(id)).thenReturn(R.success("用户删除成功"));

        R response = consumerController.deleteUser(id);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("用户删除成功");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testUpdateUserMsg() {
        ConsumerRequest request = new ConsumerRequest();
        // Mocking the service call
        when(consumerService.updateUserMsg(request)).thenReturn(R.success("用户信息更新成功"));

        R response = consumerController.updateUserMsg(request);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("用户信息更新成功");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testUpdatePassword() {
        ConsumerRequest request = new ConsumerRequest();
        // Mocking the service call
        when(consumerService.updatePassword(request)).thenReturn(R.success("密码更新成功"));

        R response = consumerController.updatePassword(request);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("密码更新成功");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testUpdateUserPic() {
        MultipartFile file = mock(MultipartFile.class);
        int id = 1;
        // Mocking the service call
        when(consumerService.updateUserAvator(file, id)).thenReturn(R.success("头像更新成功"));

        R response = consumerController.updateUserPic(file, id);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("头像更新成功");
        assertThat(response.getSuccess()).isTrue();
    }
}
