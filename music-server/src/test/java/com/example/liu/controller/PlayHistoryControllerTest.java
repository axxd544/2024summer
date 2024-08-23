package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.PlayHistoryRequest;
import com.example.liu.service.PlayHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayHistoryControllerTest {

    @Mock
    private PlayHistoryService playHistoryService;

    @InjectMocks
    private PlayHistoryController playHistoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecodePlayHistory() {
        PlayHistoryRequest request = new PlayHistoryRequest();
        R expectedResponse = R.success("Play history recorded successfully");
        when(playHistoryService.recodePlayHistory(request)).thenReturn(expectedResponse);

        R actualResponse = playHistoryController.recodePlayHistory(request);

        assertEquals(expectedResponse, actualResponse);
        verify(playHistoryService, times(1)).recodePlayHistory(request);
    }

    @Test
    public void testGetPlayHistory() {
        int userId = 1;
        R expectedResponse = R.success("Play history fetched successfully");
        when(playHistoryService.getPlayHistory(userId)).thenReturn(expectedResponse);

        R actualResponse = playHistoryController.getPlayHistory(userId);

        assertEquals(expectedResponse, actualResponse);
        verify(playHistoryService, times(1)).getPlayHistory(userId);
    }

    @Test
    public void testRecodePlayHistory_InvalidRequest() {
        PlayHistoryRequest invalidRequest = new PlayHistoryRequest();
        R expectedResponse = R.error("Invalid request");
        when(playHistoryService.recodePlayHistory(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = playHistoryController.recodePlayHistory(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(playHistoryService, times(1)).recodePlayHistory(invalidRequest);
    }

    @Test
    public void testGetPlayHistory_InvalidUserId() {
        int invalidUserId = -1;
        R expectedResponse = R.error("Invalid user ID");
        when(playHistoryService.getPlayHistory(invalidUserId)).thenReturn(expectedResponse);

        R actualResponse = playHistoryController.getPlayHistory(invalidUserId);

        assertEquals(expectedResponse, actualResponse);
        verify(playHistoryService, times(1)).getPlayHistory(invalidUserId);
    }
}
