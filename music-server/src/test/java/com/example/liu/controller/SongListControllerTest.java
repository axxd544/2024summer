package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.SongListRequest;
import com.example.liu.service.SongListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SongListControllerTest {

    @Mock
    private SongListService songListService;

    @InjectMocks
    private SongListController songListController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSongList() {
        SongListRequest request = new SongListRequest();
        R expectedResponse = R.success("Song list added successfully");
        when(songListService.addSongList(request)).thenReturn(expectedResponse);

        R actualResponse = songListController.addSongList(request);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).addSongList(request);
    }

    @Test
    public void testDeleteSongList() {
        int id = 1;
        R expectedResponse = R.success("Song list deleted successfully");
        when(songListService.deleteSongList(id)).thenReturn(expectedResponse);

        R actualResponse = songListController.deleteSongList(id);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).deleteSongList(id);
    }

    @Test
    public void testAllSongList() {
        R expectedResponse = R.success("Fetched all song lists");
        when(songListService.allSongList()).thenReturn(expectedResponse);

        R actualResponse = songListController.allSongList();

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).allSongList();
    }

    @Test
    public void testSongListOfLikeTitle() {
        String title = "Rock";
        R expectedResponse = R.success("Fetched song lists by title");
        when(songListService.likeTitle('%' + title + '%')).thenReturn(expectedResponse);

        R actualResponse = songListController.songListOfLikeTitle(title);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).likeTitle('%' + title + '%');
    }

    @Test
    public void testSongListOfStyle() {
        String style = "Pop";
        R expectedResponse = R.success("Fetched song lists by style");
        when(songListService.likeStyle('%' + style + '%')).thenReturn(expectedResponse);

        R actualResponse = songListController.songListOfStyle(style);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).likeStyle('%' + style + '%');
    }

    @Test
    public void testUpdateSongListMsg() {
        SongListRequest request = new SongListRequest();
        R expectedResponse = R.success("Song list information updated successfully");
        when(songListService.updateSongListMsg(request)).thenReturn(expectedResponse);

        R actualResponse = songListController.updateSongListMsg(request);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).updateSongListMsg(request);
    }

    @Test
    public void testUpdateSongListPic() {
        MultipartFile file = mock(MultipartFile.class);
        int id = 1;
        R expectedResponse = R.success("Song list image updated successfully");
        when(songListService.updateSongListImg(file, id)).thenReturn(expectedResponse);

        R actualResponse = songListController.updateSongListPic(file, id);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).updateSongListImg(file, id);
    }

    @Test
    public void testAddSongList_InvalidRequest() {
        SongListRequest invalidRequest = new SongListRequest();
        R expectedResponse = R.error("Invalid request");
        when(songListService.addSongList(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = songListController.addSongList(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).addSongList(invalidRequest);
    }

    @Test
    public void testDeleteSongList_InvalidId() {
        int invalidId = -1;
        R expectedResponse = R.error("Invalid song list ID");
        when(songListService.deleteSongList(invalidId)).thenReturn(expectedResponse);

        R actualResponse = songListController.deleteSongList(invalidId);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).deleteSongList(invalidId);
    }

    @Test
    public void testSongListOfLikeTitle_InvalidTitle() {
        String invalidTitle = "";
        R expectedResponse = R.error("Invalid title");
        when(songListService.likeTitle('%' + invalidTitle + '%')).thenReturn(expectedResponse);

        R actualResponse = songListController.songListOfLikeTitle(invalidTitle);

        assertEquals(expectedResponse, actualResponse);
        verify(songListService, times(1)).likeTitle('%' + invalidTitle + '%');
    }
}
