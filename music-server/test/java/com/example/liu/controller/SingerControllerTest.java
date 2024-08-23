package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.SingerRequest;
import com.example.liu.service.SingerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SingerControllerTest {

    @Mock
    private SingerService singerService;

    @InjectMocks
    private SingerController singerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSinger() {
        SingerRequest request = new SingerRequest();
        R expectedResponse = R.success("Singer added successfully");
        when(singerService.addSinger(request)).thenReturn(expectedResponse);

        R actualResponse = singerController.addSinger(request);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).addSinger(request);
    }

    @Test
    public void testDeleteSinger() {
        int id = 1;
        R expectedResponse = R.success("Singer deleted successfully");
        when(singerService.deleteSinger(id)).thenReturn(expectedResponse);

        R actualResponse = singerController.deleteSinger(id);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).deleteSinger(id);
    }

    @Test
    public void testAllSinger() {
        R expectedResponse = R.success("Fetched all singers");
        when(singerService.allSinger()).thenReturn(expectedResponse);

        R actualResponse = singerController.allSinger();

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).allSinger();
    }

    @Test
    public void testSingerOfName() {
        String name = "John";
        R expectedResponse = R.success("Singer found by name");
        when(singerService.singerOfName(name)).thenReturn(expectedResponse);

        R actualResponse = singerController.singerOfName(name);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).singerOfName(name);
    }

    @Test
    public void testSingerOfSex() {
        int sex = 1;
        R expectedResponse = R.success("Singers found by sex");
        when(singerService.singerOfSex(sex)).thenReturn(expectedResponse);

        R actualResponse = singerController.singerOfSex(sex);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).singerOfSex(sex);
    }

    @Test
    public void testUpdateSingerMsg() {
        SingerRequest request = new SingerRequest();
        R expectedResponse = R.success("Singer information updated successfully");
        when(singerService.updateSingerMsg(request)).thenReturn(expectedResponse);

        R actualResponse = singerController.updateSingerMsg(request);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).updateSingerMsg(request);
    }

    @Test
    public void testUpdateSingerPic() {
        MultipartFile file = mock(MultipartFile.class);
        int id = 1;
        R expectedResponse = R.success("Singer picture updated successfully");
        when(singerService.updateSingerPic(file, id)).thenReturn(expectedResponse);

        R actualResponse = singerController.updateSingerPic(file, id);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).updateSingerPic(file, id);
    }

    @Test
    public void testAddSinger_InvalidRequest() {
        SingerRequest invalidRequest = new SingerRequest();
        R expectedResponse = R.error("Invalid request");
        when(singerService.addSinger(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = singerController.addSinger(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).addSinger(invalidRequest);
    }

    @Test
    public void testDeleteSinger_InvalidId() {
        int invalidId = -1;
        R expectedResponse = R.error("Invalid singer ID");
        when(singerService.deleteSinger(invalidId)).thenReturn(expectedResponse);

        R actualResponse = singerController.deleteSinger(invalidId);

        assertEquals(expectedResponse, actualResponse);
        verify(singerService, times(1)).deleteSinger(invalidId);
    }
}
