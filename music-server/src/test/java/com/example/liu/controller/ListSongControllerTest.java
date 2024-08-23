package com.example.liu.controller;

import com.alibaba.excel.EasyExcel;
import com.example.liu.common.R;
import com.example.liu.model.domain.SongList;
import com.example.liu.model.request.ListSongRequest;
import com.example.liu.service.ListSongService;
import com.example.liu.service.SongListService;
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

public class ListSongControllerTest {

    @Mock
    private ListSongService listSongService;

    @Mock
    private SongListService service;

    @InjectMocks
    private ListSongController listSongController;

    public ListSongControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddListSongSuccess() {
        ListSongRequest request = new ListSongRequest();
        R expectedResponse = R.success("Song added");
        when(listSongService.addListSong(request)).thenReturn(expectedResponse);

        R response = listSongController.addListSong(request);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testDeleteListSongSuccess() {
        int songId = 1;
        int songListId = 2;
        R expectedResponse = R.success("Song deleted");
        when(listSongService.deleteListSong(songId, songListId)).thenReturn(expectedResponse);

        R response = listSongController.deleteListSong(songId, songListId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testListSongOfSongIdSuccess() {
        int songListId = 1;
        R expectedResponse = R.success("List of songs");
        when(listSongService.listSongOfSongId(songListId)).thenReturn(expectedResponse);

        R response = listSongController.listSongOfSongId(songListId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testUpdateListSongMsgSuccess() {
        ListSongRequest request = new ListSongRequest();
        R expectedResponse = R.success("Song info updated");
        when(listSongService.updateListSongMsg(request)).thenReturn(expectedResponse);

        R response = listSongController.updateListSongMsg(request);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetExcleSuccess() throws IOException {
        List<SongList> data = Collections.emptyList();
        when(service.findAllSong()).thenReturn(data);

        File tempFile = File.createTempFile("SongList", ".xlsx");
        tempFile.deleteOnExit();
        EasyExcel.write(tempFile.getAbsolutePath(), SongList.class).sheet("模板").doWrite(data);
        
        HttpServletRequest request = mock(HttpServletRequest.class);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(tempFile));
        byte[] content = Files.readAllBytes(tempFile.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + tempFile.getName());

        ResponseEntity<Resource> expectedResponse = ResponseEntity.ok()
                .headers(headers)
                .contentLength(content.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

        ResponseEntity<Resource> response = listSongController.getExcle(request);

       
    }

    @Test
    public void testGetExcleFailure() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);

    }
}
