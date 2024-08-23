package com.example.liu.service;

import com.example.liu.common.R;
import com.example.liu.mapper.UserSupportMapper;
import com.example.liu.model.domain.UserSupport;
import com.example.liu.model.request.UserSupportRequest;
import com.example.liu.service.impl.UserSupportServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class UserSupportServiceTest {

    @InjectMocks
    private UserSupportServiceImpl userSupportService;

    @Mock
    private UserSupportMapper userSupportMapper;

    UserSupportServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsUserSupportComment_WhenRecordExists() {
        UserSupportRequest request = new UserSupportRequest();
        request.setCommentId(1);
        request.setUserId(1);

        doReturn(1L).when(userSupportMapper).selectCount(any());

        R result = userSupportService.isUserSupportComment(request);
        assertEquals("您已取消点赞", result.getMessage());
        assertEquals(true, result.getData());
    }

    @Test
    public void testIsUserSupportComment_WhenRecordDoesNotExist() {
        UserSupportRequest request = new UserSupportRequest();
        request.setCommentId(1);
        request.setUserId(1);

        doReturn(0L).when(userSupportMapper).selectCount(any());

        R result = userSupportService.isUserSupportComment(request);
        assertEquals("您已点赞", result.getMessage());
        assertEquals(false, result.getData());
    }

    @Test
    public void testInsertCommentSupport_Success() {
        UserSupportRequest request = new UserSupportRequest();
        request.setCommentId(1);
        request.setUserId(1);
        UserSupport userSupport = new UserSupport();
        BeanUtils.copyProperties(request, userSupport);

        doReturn(1).when(userSupportMapper).insert(userSupport);

        R result = userSupportService.insertCommentSupport(request);
        assertEquals("添加记录成功", result.getMessage());
    }

    @Test
    public void testInsertCommentSupport_Failure() {
        UserSupportRequest request = new UserSupportRequest();
        request.setCommentId(1);
        request.setUserId(1);
        UserSupport userSupport = new UserSupport();
        BeanUtils.copyProperties(request, userSupport);

        doReturn(0).when(userSupportMapper).insert(userSupport);

        R result = userSupportService.insertCommentSupport(request);
        assertEquals("添加时,发生异常", result.getMessage());
    }

    @Test
    public void testDeleteCommentSupport_Success() {
        UserSupportRequest request = new UserSupportRequest();
        request.setCommentId(1);
        request.setUserId(1);

        doReturn(1).when(userSupportMapper).delete(any());

        R result = userSupportService.deleteCommentSupport(request);
        assertEquals("删除记录成功", result.getMessage());
    }

    @Test
    public void testDeleteCommentSupport_Failure() {
        UserSupportRequest request = new UserSupportRequest();
        request.setCommentId(1);
        request.setUserId(1);

        doReturn(0).when(userSupportMapper).delete(any());

        R result = userSupportService.deleteCommentSupport(request);
        assertEquals("删除记录发生异常", result.getMessage());
    }
}
