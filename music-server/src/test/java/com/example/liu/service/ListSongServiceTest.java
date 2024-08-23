package com.example.liu.service;
import com.example.liu.service.impl.*;
import com.example.liu.model.domain.ListSong;
import com.example.liu.model.request.ListSongRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.liu.common.R;
import com.example.liu.mapper.ListSongMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ListSongServiceTest {

    @InjectMocks
    private ListSongServiceImpl listSongService;

    @Mock
    private ListSongMapper listSongMapper;

    @Test
    public void testAddListSong_Success() {
        ListSongRequest request = new ListSongRequest();
        request.setSongId(1);
        request.setSongListId(2);

        doReturn(1).when(listSongMapper).insert(any(ListSong.class));

        R result = listSongService.addListSong(request);
        assertEquals("添加成功", result.getMessage());
    }

    @Test
    public void testAddListSong_Failure() {
        ListSongRequest request = new ListSongRequest();
        request.setSongId(1);
        request.setSongListId(2);

        doReturn(0).when(listSongMapper).insert(any(ListSong.class));

        R result = listSongService.addListSong(request);
        assertEquals("添加失败", result.getMessage());
    }

    @Test
    public void testUpdateListSongMsg_Success() {
        ListSongRequest request = new ListSongRequest();
        request.setSongId(1);
        request.setSongListId(2);

        doReturn(1).when(listSongMapper).updateById(any(ListSong.class));

        R result = listSongService.updateListSongMsg(request);
        assertEquals("修改成功", result.getMessage());
    }

    @Test
    public void testUpdateListSongMsg_Failure() {
        ListSongRequest request = new ListSongRequest();
        request.setSongId(1);
        request.setSongListId(2);

        doReturn(0).when(listSongMapper).updateById(any(ListSong.class));

        R result = listSongService.updateListSongMsg(request);
        assertEquals("修改失败", result.getMessage());
    }

    @Test
    public void testDeleteListSong_Success() {
        doReturn(1).when(listSongMapper).delete(any());

        R result = listSongService.deleteListSong(1, 2);
        assertEquals("删除成功", result.getMessage());
    }

    @Test
    public void testDeleteListSong_Failure() {
        doReturn(0).when(listSongMapper).delete(any());

        R result = listSongService.deleteListSong(1, 2);
        assertEquals("删除失败", result.getMessage());
    }

    @Test
    public void testAllListSong_Success() {
        doReturn(Collections.emptyList()).when(listSongMapper).selectList(null);

        List<ListSong> result = listSongService.allListSong();
        assertEquals(0, result.size());
    }

    @Test
    public void testListSongOfSongId_Success() {
        doReturn(Collections.emptyList()).when(listSongMapper).selectList(any());

        R result = listSongService.listSongOfSongId(2);
        assertEquals("查询成功", result.getMessage());
    }
}
