package com.example.liu.service;
import com.example.liu.service.impl.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;
import com.example.liu.mapper.ConsumerMapper;
import com.example.liu.model.domain.Consumer;
import com.example.liu.model.request.ConsumerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static com.example.liu.constant.Constants.SALT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

public class ConsumerServiceTest {

    @Mock
    private ConsumerMapper consumerMapper;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ConsumerServiceImpl consumerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateUserMsg_Success() {
        ConsumerRequest request = new ConsumerRequest();
        request.setId(1);
        request.setUsername("testUser");

        doReturn(1).when(consumerMapper).updateById(any(Consumer.class));

        R result = consumerService.updateUserMsg(request);

        assertEquals("修改成功", result.getMessage());
    }

    @Test
    public void testUpdateUserMsg_Failure() {
        ConsumerRequest request = new ConsumerRequest();
        request.setId(1);
        request.setUsername("testUser");

        doReturn(0).when(consumerMapper).updateById(any(Consumer.class));

        R result = consumerService.updateUserMsg(request);

    }

    @Test
    public void testUpdatePassword_Success() {
        ConsumerRequest request = new ConsumerRequest();
        request.setId(1);
        request.setUsername("testUser");
        request.setOldPassword("oldPass");
        request.setPassword("newPass");

    }

    @Test
    public void testUpdatePassword_Failure() {
        ConsumerRequest request = new ConsumerRequest();
        request.setId(1);
        request.setUsername("testUser");
        request.setOldPassword("oldPass");
        request.setPassword("newPass");

        R result = consumerService.updatePassword(request);

    }

    @Test
    public void testUpdatePassword01_Success() {
        ConsumerRequest request = new ConsumerRequest();
        request.setId(1);
        request.setPassword("newPass");
    }

    @Test
    public void testUpdatePassword01_Failure() {
        ConsumerRequest request = new ConsumerRequest();
        request.setId(1);
        request.setPassword("newPass");

        doReturn(0).when(consumerMapper).updateById(any(Consumer.class));

        R result = consumerService.updatePassword01(request);

        assertEquals("密码修改失败", result.getMessage());
    }

   

    @Test
    public void testExistUser_True() {
        String username = "testUser";

        doReturn(1L).when(consumerMapper).selectCount(any(QueryWrapper.class));

        boolean exists = consumerService.existUser(username);

        assertTrue(exists);
    }

    @Test
    public void testExistUser_False() {
        String username = "testUser";

        doReturn(0L).when(consumerMapper).selectCount(any(QueryWrapper.class));

        boolean exists = consumerService.existUser(username);

        assertFalse(exists);
    }

    @Test
    public void testVerityPasswd_True() {
        String username = "testUser";
        String password = "password123";
        String hashedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));

        doReturn(1L).when(consumerMapper).selectCount(any(QueryWrapper.class));

        boolean isValid = consumerService.verityPasswd(username, password);

        assertTrue(isValid);
    }

    @Test
    public void testVerityPasswd_False() {
        String username = "testUser";
        String password = "wrongPassword";

        doReturn(0L).when(consumerMapper).selectCount(any(QueryWrapper.class));

        boolean isValid = consumerService.verityPasswd(username, password);

        assertFalse(isValid);
    }

    @Test
    public void testDeleteUser_Success() {
        int userId = 1;

        doReturn(1).when(consumerMapper).deleteById(userId);

        R result = consumerService.deleteUser(userId);

        assertEquals("删除成功", result.getMessage());
    }

    @Test
    public void testDeleteUser_Failure() {
        int userId = 1;

        doReturn(0).when(consumerMapper).deleteById(userId);

        R result = consumerService.deleteUser(userId);

        assertEquals("删除失败", result.getMessage());
    }

    @Test
    public void testAllUser() {
        List<Consumer> consumerList = Collections.singletonList(new Consumer());

        doReturn(consumerList).when(consumerMapper).selectList(null);

        R result = consumerService.allUser();

        assertEquals(consumerList, result.getData());
    }

    @Test
    public void testUserOfId() {
        int userId = 1;
        List<Consumer> consumerList = Collections.singletonList(new Consumer());

        doReturn(consumerList).when(consumerMapper).selectList(any(QueryWrapper.class));

        R result = consumerService.userOfId(userId);

        assertEquals(consumerList, result.getData());
    }

    @Test
    public void testLikeUsername() {
        String username = "testUser";
        List<Consumer> consumerList = Collections.singletonList(new Consumer());

        doReturn(consumerList).when(consumerMapper).selectList(any(QueryWrapper.class));

        R result = consumerService.likeUsername(username);

        assertEquals(consumerList, result.getData());
    }


    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Consumer consumer = new Consumer();
        consumer.setEmail(email);

        doReturn(consumer).when(consumerMapper).selectOne(any(QueryWrapper.class));

        Consumer result = consumerService.findByEmail(email);

        assertEquals(consumer, result);
    }
}
