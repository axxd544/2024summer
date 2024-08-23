package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.RankListRequest;
import com.example.liu.service.RankListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RankListControllerTest {

    @Mock
    private RankListService rankListService;

    @InjectMocks
    private RankListController rankListController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRank() {
        RankListRequest request = new RankListRequest();
        R expectedResponse = R.success("Rank added successfully");
        when(rankListService.addRank(request)).thenReturn(expectedResponse);

        R actualResponse = rankListController.addRank(request);

        assertEquals(expectedResponse, actualResponse);
        verify(rankListService, times(1)).addRank(request);
    }

    @Test
    public void testRankOfSongListId() {
        Long songListId = 1L;
        R expectedResponse = R.success("Rank fetched successfully");
        when(rankListService.rankOfSongListId(songListId)).thenReturn(expectedResponse);

        R actualResponse = rankListController.rankOfSongListId(songListId);

        assertEquals(expectedResponse, actualResponse);
        verify(rankListService, times(1)).rankOfSongListId(songListId);
    }

    @Test
    public void testAddRank_InvalidRequest() {
        RankListRequest invalidRequest = new RankListRequest();
        R expectedResponse = R.error("Invalid request");
        when(rankListService.addRank(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = rankListController.addRank(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(rankListService, times(1)).addRank(invalidRequest);
    }

    @Test
    public void testRankOfSongListId_InvalidId() {
        Long invalidSongListId = -1L;
        R expectedResponse = R.error("Invalid song list ID");
        when(rankListService.rankOfSongListId(invalidSongListId)).thenReturn(expectedResponse);

        R actualResponse = rankListController.rankOfSongListId(invalidSongListId);

        assertEquals(expectedResponse, actualResponse);
        verify(rankListService, times(1)).rankOfSongListId(invalidSongListId);
    }
}
