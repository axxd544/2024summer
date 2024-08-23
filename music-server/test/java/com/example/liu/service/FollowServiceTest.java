package com.example.liu.service;
import com.example.liu.service.impl.*;
import com.example.liu.model.domain.Follow;
import com.example.liu.model.request.FollowRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.liu.common.R;
import com.example.liu.mapper.FollowMapper;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FollowServiceTest {

    @InjectMocks
    private FollowServiceImpl followService;

    @Mock
    private FollowMapper followMapper;

    @Test
    public void testAddFollow_Success() {
        FollowRequest request = new FollowRequest();
        request.setFollowerId(1);
        request.setFollowedId(2);

        doReturn(null).when(followMapper).selectOne(any());
        doReturn(1).when(followMapper).insert(any(Follow.class));

        R result = followService.addFollow(request);
        assertEquals("关注成功", result.getMessage());
    }

    @Test
    public void testAddFollow_AlreadyExists() {
        FollowRequest request = new FollowRequest();
        request.setFollowerId(1);
        request.setFollowedId(2);

        doReturn(new Follow()).when(followMapper).selectOne(any());

        R result = followService.addFollow(request);
        assertEquals("已关注，请勿重复关注", result.getMessage());
    }

    @Test
    public void testDeleteFollow_Success() {
        doReturn(1).when(followMapper).delete(any());

        R result = followService.deleteFollow(1, 2);
        assertEquals("取消关注成功", result.getMessage());
    }

    @Test
    public void testDeleteFollow_Failure() {
        doReturn(0).when(followMapper).delete(any());

        R result = followService.deleteFollow(1, 2);
        assertEquals("取消关注失败", result.getMessage());
    }

    @Test
    public void testListFollowByFollowedId_Success() {
        doReturn(Collections.emptyList()).when(followMapper).selectList(any());

        R result = followService.listFollowByFollowedId(2);
        assertEquals("查询成功", result.getMessage());
    }

    @Test
    public void testListFollowByFollowerId_Success() {
        doReturn(Collections.emptyList()).when(followMapper).selectList(any());

        R result = followService.listFollowByFollowerId(1);
        assertEquals("查询成功", result.getMessage());
    }
}
