package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.SongRequest;
import com.example.liu.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SongControllerTest {

    @Mock
    private SongService songService;

    @InjectMocks
    private SongController songController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSong() {
        SongRequest request = new SongRequest();
        MultipartFile lrcfile = mock(MultipartFile.class);
        MultipartFile mpfile = mock(MultipartFile.class);
        R expectedResponse = R.success("Song added successfully");
        when(songService.addSong(request, lrcfile, mpfile)).thenReturn(expectedResponse);

        R actualResponse = songController.addSong(request, lrcfile, mpfile);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).addSong(request, lrcfile, mpfile);
    }

    @Test
    public void testDeleteSong() {
        int id = 1;
        R expectedResponse = R.success("Song deleted successfully");
        when(songService.deleteSong(id)).thenReturn(expectedResponse);

        R actualResponse = songController.deleteSong(id);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).deleteSong(id);
    }

    @Test
    public void testDeleteSongByManager() {
        int id = 1;
        int complainterId = 2;
        R expectedResponse = R.success("Song deleted by manager");
        when(songService.deleteSongByManager(id, complainterId)).thenReturn(expectedResponse);

        R actualResponse = songController.deleteSongByManager(id, complainterId);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).deleteSongByManager(id, complainterId);
    }

    @Test
    public void testUndeleteSongByManager() {
        int id = 1;
        int complainterId = 2;
        int appealerId = 3;
        R expectedResponse = R.success("Song undeleted by manager");
        when(songService.unDeleteSongByManager(id, complainterId, appealerId)).thenReturn(expectedResponse);

        R actualResponse = songController.undeleteSongByManager(id, complainterId, appealerId);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).unDeleteSongByManager(id, complainterId, appealerId);
    }

    @Test
    public void testAllSong() {
        R expectedResponse = R.success("Fetched all songs");
        when(songService.allSong()).thenReturn(expectedResponse);

        R actualResponse = songController.allSong();

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).allSong();
    }

    @Test
    public void testSongOfId() {
        int id = 1;
        R expectedResponse = R.success("Fetched song by ID");
        when(songService.songOfId(id)).thenReturn(expectedResponse);

        R actualResponse = songController.songOfId(id);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).songOfId(id);
    }

    @Test
    public void testSongOfSingerId() {
        int singerId = 1;
        R expectedResponse = R.success("Fetched songs by singer ID");
        when(songService.songOfSingerId(singerId)).thenReturn(expectedResponse);

        R actualResponse = songController.songOfSingerId(singerId);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).songOfSingerId(singerId);
    }

    @Test
    public void testSongOfSingerName() {
        String name = "John";
        R expectedResponse = R.success("Fetched songs by singer name");
        when(songService.songOfSingerName('%' + name + '%')).thenReturn(expectedResponse);

        R actualResponse = songController.songOfSingerName(name);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).songOfSingerName('%' + name + '%');
    }

    @Test
    public void testUpdateSongMsg() {
        SongRequest request = new SongRequest();
        R expectedResponse = R.success("Song information updated successfully");
        when(songService.updateSongMsg(request)).thenReturn(expectedResponse);

        R actualResponse = songController.updateSongMsg(request);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).updateSongMsg(request);
    }

    @Test
    public void testUpdateSongPic() {
        MultipartFile file = mock(MultipartFile.class);
        int id = 1;
        R expectedResponse = R.success("Song picture updated successfully");
        when(songService.updateSongPic(file, id)).thenReturn(expectedResponse);

        R actualResponse = songController.updateSongPic(file, id);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).updateSongPic(file, id);
    }

    @Test
    public void testUpdateSongUrl() {
        MultipartFile file = mock(MultipartFile.class);
        int id = 1;
        R expectedResponse = R.success("Song URL updated successfully");
        when(songService.updateSongUrl(file, id)).thenReturn(expectedResponse);

        R actualResponse = songController.updateSongUrl(file, id);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).updateSongUrl(file, id);
    }

    @Test
    public void testUpdateSongLrc() {
        MultipartFile lrcFile = mock(MultipartFile.class);
        int id = 1;
        R expectedResponse = R.success("Song lyrics updated successfully");
        when(songService.updateSongLrc(lrcFile, id)).thenReturn(expectedResponse);

        R actualResponse = songController.updateSongLrc(lrcFile, id);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).updateSongLrc(lrcFile, id);
    }

    @Test
    public void testExportSongLrc() {
        int id = 1;
        HttpServletResponse response = mock(HttpServletResponse.class);
        doNothing().when(songService).exportSongLrc(id, response);

        songController.exportSongLrc(id, response);

        verify(songService, times(1)).exportSongLrc(id, response);
    }

    @Test
    public void testAddSong_InvalidRequest() {
        SongRequest invalidRequest = new SongRequest();
        MultipartFile lrcfile = mock(MultipartFile.class);
        MultipartFile mpfile = mock(MultipartFile.class);
        R expectedResponse = R.error("Invalid request");
        when(songService.addSong(invalidRequest, lrcfile, mpfile)).thenReturn(expectedResponse);

        R actualResponse = songController.addSong(invalidRequest, lrcfile, mpfile);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).addSong(invalidRequest, lrcfile, mpfile);
    }

    @Test
    public void testDeleteSong_InvalidId() {
        int invalidId = -1;
        R expectedResponse = R.error("Invalid song ID");
        when(songService.deleteSong(invalidId)).thenReturn(expectedResponse);

        R actualResponse = songController.deleteSong(invalidId);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).deleteSong(invalidId);
    }

    @Test
    public void testDeleteSongByManager_InvalidParams() {
        int invalidId = -1;
        int complainterId = -1;
        R expectedResponse = R.error("Invalid parameters");
        when(songService.deleteSongByManager(invalidId, complainterId)).thenReturn(expectedResponse);

        R actualResponse = songController.deleteSongByManager(invalidId, complainterId);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).deleteSongByManager(invalidId, complainterId);
    }

    @Test
    public void testUndeleteSongByManager_InvalidParams() {
        int invalidId = -1;
        int complainterId = -1;
        int appealerId = -1;
        R expectedResponse = R.error("Invalid parameters");
        when(songService.unDeleteSongByManager(invalidId, complainterId, appealerId)).thenReturn(expectedResponse);

        R actualResponse = songController.undeleteSongByManager(invalidId, complainterId, appealerId);

        assertEquals(expectedResponse, actualResponse);
        verify(songService, times(1)).unDeleteSongByManager(invalidId, complainterId, appealerId);
    }
}
