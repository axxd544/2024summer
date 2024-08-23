package com.example.liu.service;
import com.example.liu.service.impl.*;
import com.example.liu.model.domain.ListSongConsumer;
import com.example.liu.model.request.ListSongConsumerRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.liu.common.R;
import com.example.liu.mapper.ListSongConsumerMapper;
import com.example.liu.mapper.SongListConsumerMapper;
import com.example.liu.mapper.ConsumerMapper;
import com.example.liu.mapper.FollowMapper;
import com.example.liu.service.NotificationService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ListSongConsumerServiceTest {

    @InjectMocks
    private ListSongConsumerImpl listSongConsumerService;

    @Mock
    private ListSongConsumerMapper listSongConsumerMapper;

    @Mock
    private SongListConsumerMapper songListConsumerMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    @Mock
    private FollowMapper followMapper;

    @Mock
    private NotificationService notificationService;

    @Test
    public void testAddListSongConsumer_Success() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        request.setSongId(1);
        request.setSongListConsumerId(2);

    }

    @Test
    public void testAddListSongConsumer_Failure() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        request.setSongId(1);
        request.setSongListConsumerId(2);

        doReturn(0).when(listSongConsumerMapper).insert(any(ListSongConsumer.class));

        R result = listSongConsumerService.addListSongConsumer(request);
        assertEquals("添加失败", result.getMessage());
    }

    @Test
    public void testUpdateListSongConsumerMsg_Success() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        request.setSongId(1);
        request.setSongListConsumerId(2);

    }

    @Test
    public void testUpdateListSongConsumerMsg_Failure() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        request.setSongId(1);
        request.setSongListConsumerId(2);

        doReturn(0).when(listSongConsumerMapper).updateById(any(ListSongConsumer.class));

        R result = listSongConsumerService.updateListSongConsumerMsg(request);
        assertEquals("修改失败", result.getMessage());
    }

    @Test
    public void testAllListSongConsumer_Success() {
        doReturn(Collections.emptyList()).when(listSongConsumerMapper).selectList(null);

        List<ListSongConsumer> result = listSongConsumerService.allListSongConsumer();
        assertEquals(0, result.size());
    }

    @Test
    public void testListSongConsumerOfSongId_Success() {
        doReturn(Collections.emptyList()).when(listSongConsumerMapper).selectList(any());

        R result = listSongConsumerService.listSongConsumerOfSongId(2);
        assertEquals("查询成功", result.getMessage());
    }
}
