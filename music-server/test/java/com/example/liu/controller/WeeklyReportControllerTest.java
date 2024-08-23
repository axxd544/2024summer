package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.WeeklyReportRequest;
import com.example.liu.service.WeeklyReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WeeklyReportControllerTest {

    @Mock
    private WeeklyReportService weeklyReportService;

    @InjectMocks
    private WeeklyReportController weeklyReportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateWeeklyReport() {
        WeeklyReportRequest request = new WeeklyReportRequest();
        R expectedResponse = R.success("Weekly report generated successfully");
        when(weeklyReportService.generateWeeklyReport(request)).thenReturn(expectedResponse);

        R actualResponse = weeklyReportController.generateWeeklyReport(request);

        assertEquals(expectedResponse, actualResponse);
        verify(weeklyReportService, times(1)).generateWeeklyReport(request);
    }

    @Test
    public void testGetWeeklyReport() {
        int userId = 1;
        R expectedResponse = R.success("Weekly report retrieved successfully");
        when(weeklyReportService.getWeeklyReport(userId)).thenReturn(expectedResponse);

        R actualResponse = weeklyReportController.getWeeklyReport(userId);

        assertEquals(expectedResponse, actualResponse);
        verify(weeklyReportService, times(1)).getWeeklyReport(userId);
    }

    @Test
    public void testGenerateWeeklyReport_InvalidRequest() {
        WeeklyReportRequest invalidRequest = new WeeklyReportRequest();
        R expectedResponse = R.error("Invalid report request");
        when(weeklyReportService.generateWeeklyReport(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = weeklyReportController.generateWeeklyReport(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(weeklyReportService, times(1)).generateWeeklyReport(invalidRequest);
    }

    @Test
    public void testGetWeeklyReport_InvalidUserId() {
        int invalidUserId = -1;
        R expectedResponse = R.error("Invalid user ID");
        when(weeklyReportService.getWeeklyReport(invalidUserId)).thenReturn(expectedResponse);

        R actualResponse = weeklyReportController.getWeeklyReport(invalidUserId);

        assertEquals(expectedResponse, actualResponse);
        verify(weeklyReportService, times(1)).getWeeklyReport(invalidUserId);
    }
}
