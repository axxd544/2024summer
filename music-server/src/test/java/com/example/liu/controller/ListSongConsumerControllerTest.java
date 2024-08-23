package com.example.liu.controller;

import com.alibaba.excel.EasyExcel;
import com.example.liu.common.R;
import com.example.liu.model.domain.SongListConsumer;
import com.example.liu.model.request.ListSongConsumerRequest;
import com.example.liu.service.ListSongConsumerService;
import com.example.liu.service.SongListConsumerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListSongConsumerControllerTest {

    @Mock
    private SongListConsumerService service;

    @Mock
    private ListSongConsumerService listSongConsumerService;

    @InjectMocks
    private ListSongConsumerController listSongConsumerController;

    public ListSongConsumerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddListSongConsumerSuccess() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        R expectedResponse = R.success("Song added");
        when(listSongConsumerService.addListSongConsumer(request)).thenReturn(expectedResponse);

        R response = listSongConsumerController.addListSongConsumer(request);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testDeleteListSongConsumerSuccess() {
        int songId = 1;
        int songListConsumerId = 2;
        R expectedResponse = R.success("Song deleted");
        when(listSongConsumerService.deleteListSongConsumer(songId, songListConsumerId)).thenReturn(expectedResponse);

        R response = listSongConsumerController.deleteListSongConsumer(songId, songListConsumerId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testListSongConsumerOfSongIdSuccess() {
        int songListConsumerId = 1;
        R expectedResponse = R.success("List of songs");
        when(listSongConsumerService.listSongConsumerOfSongId(songListConsumerId)).thenReturn(expectedResponse);

        R response = listSongConsumerController.listSongConsumerOfSongId(songListConsumerId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testUpdateListSongConsumerMsgSuccess() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        R expectedResponse = R.success("Song info updated");
        when(listSongConsumerService.updateListSongConsumerMsg(request)).thenReturn(expectedResponse);

        R response = listSongConsumerController.updateListSongConsumerMsg(request);

        assertEquals(expectedResponse, response);
    }

   

   
}
