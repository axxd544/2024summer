package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.SongListConsumerRequest;
import com.example.liu.service.SongListConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SongListConsumerControllerTest {

    @Mock
    private SongListConsumerService songListConsumerService;

    @InjectMocks
    private SongListConsumerController songListConsumerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSongListConsumer() {
        SongListConsumerRequest request = new SongListConsumerRequest();
        R expectedResponse = R.success("Song list consumer added successfully");
        when(songListConsumerService.addSongListConsumer(request)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.addSongListConsumer(request);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).addSongListConsumer(request);
    }

    @Test
    public void testDeleteSongListConsumer() {
        int id = 1;
        R expectedResponse = R.success("Song list consumer deleted successfully");
        when(songListConsumerService.deleteSongListConsumer(id)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.deleteSongListConsumer(id);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).deleteSongListConsumer(id);
    }

    @Test
    public void testDeleteSongListConsumerByManager() {
        int id = 1;
        int complainterId = 2;
        R expectedResponse = R.success("Song list consumer deleted by manager");
        when(songListConsumerService.deleteSongListConsumerByManager(id, complainterId)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.deleteSongListConsumerByManager(id, complainterId);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).deleteSongListConsumerByManager(id, complainterId);
    }

    @Test
    public void testUnDeleteSongListConsumerByManager() {
        int id = 1;
        int complainterId = 2;
        int applealerId = 3;
        R expectedResponse = R.success("Song list consumer undeleted by manager");
        when(songListConsumerService.unDeleteSongListConsumerByManager(id, complainterId, applealerId)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.unDeleteSongListConsumerByManager(id, complainterId, applealerId);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).unDeleteSongListConsumerByManager(id, complainterId, applealerId);
    }

    @Test
    public void testAllSongListConsumer() {
        R expectedResponse = R.success("Fetched all song list consumers");
        when(songListConsumerService.allSongListConsumer()).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.allSongListConsumer();

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).allSongListConsumer();
    }

    @Test
    public void testSongListConsumerOfLikeTitle() {
        String title = "Rock";
        R expectedResponse = R.success("Fetched song list consumers by title");
        when(songListConsumerService.likeTitle('%' + title + '%')).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.songListConsumerOfLikeTitle(title);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).likeTitle('%' + title + '%');
    }

    @Test
    public void testSongListConsumerOfStyle() {
        String style = "Pop";
        R expectedResponse = R.success("Fetched song list consumers by style");
        when(songListConsumerService.likeStyle('%' + style + '%')).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.songListConsumerOfStyle(style);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).likeStyle('%' + style + '%');
    }

    @Test
    public void testSongListConsumerOfUserId() {
        Integer userId = 1;
        R expectedResponse = R.success("Fetched song list consumers by user ID");
        when(songListConsumerService.eqUserId(userId)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.songListConsumerOfUserId(userId);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).eqUserId(userId);
    }

    @Test
    public void testUpdateSongListConsumerMsg() {
        SongListConsumerRequest request = new SongListConsumerRequest();
        R expectedResponse = R.success("Song list consumer information updated successfully");
        when(songListConsumerService.updateSongListConsumerMsg(request)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.updateSongListConsumerMsg(request);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).updateSongListConsumerMsg(request);
    }

    @Test
    public void testUpdateSongListConsumerPic() {
        MultipartFile file = mock(MultipartFile.class);
        int id = 1;
        R expectedResponse = R.success("Song list consumer picture updated successfully");
        when(songListConsumerService.updateSongListConsumerImg(file, id)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.updateSongListConsumerPic(file, id);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).updateSongListConsumerImg(file, id);
    }

    @Test
    public void testSongListConsumerOfId() {
        Integer id = 1;
        R expectedResponse = R.success("Fetched song list consumer by ID");
        when(songListConsumerService.byId(id)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.songListConsumerOfId(id);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).byId(id);
    }

    @Test
    public void testAddSongListConsumer_InvalidRequest() {
        SongListConsumerRequest invalidRequest = new SongListConsumerRequest();
        R expectedResponse = R.error("Invalid request");
        when(songListConsumerService.addSongListConsumer(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.addSongListConsumer(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).addSongListConsumer(invalidRequest);
    }

    @Test
    public void testDeleteSongListConsumer_InvalidId() {
        int invalidId = -1;
        R expectedResponse = R.error("Invalid song list consumer ID");
        when(songListConsumerService.deleteSongListConsumer(invalidId)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.deleteSongListConsumer(invalidId);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).deleteSongListConsumer(invalidId);
    }

    @Test
    public void testDeleteSongListConsumerByManager_InvalidParams() {
        int invalidId = -1;
        int complainterId = -1;
        R expectedResponse = R.error("Invalid parameters");
        when(songListConsumerService.deleteSongListConsumerByManager(invalidId, complainterId)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.deleteSongListConsumerByManager(invalidId, complainterId);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).deleteSongListConsumerByManager(invalidId, complainterId);
    }

    @Test
    public void testUnDeleteSongListConsumerByManager_InvalidParams() {
        int invalidId = -1;
        int complainterId = -1;
        int applealerId = -1;
        R expectedResponse = R.error("Invalid parameters");
        when(songListConsumerService.unDeleteSongListConsumerByManager(invalidId, complainterId, applealerId)).thenReturn(expectedResponse);

        R actualResponse = songListConsumerController.unDeleteSongListConsumerByManager(invalidId, complainterId, applealerId);

        assertEquals(expectedResponse, actualResponse);
        verify(songListConsumerService, times(1)).unDeleteSongListConsumerByManager(invalidId, complainterId, applealerId);
    }
}
