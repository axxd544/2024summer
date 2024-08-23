package com.example.liu.service;

import com.example.liu.common.R;
import com.example.liu.mapper.ConsumerMapper;
import com.example.liu.mapper.WeeklyReportMapper;
import com.example.liu.model.domain.Consumer;
import com.example.liu.model.domain.WeeklyReport;
import com.example.liu.model.request.WeeklyReportRequest;
import com.example.liu.service.impl.WeeklyReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class WeeklyReportServiceTest {

    @InjectMocks
    private WeeklyReportServiceImpl weeklyReportService;

    @Mock
    private WeeklyReportMapper weeklyReportMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    WeeklyReportServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateWeeklyReport_UserNotFound() {
        WeeklyReportRequest request = new WeeklyReportRequest();
        request.setUserId(1);

        doReturn(null).when(consumerMapper).selectById(eq(1));

        R result = weeklyReportService.generateWeeklyReport(request);
        assertEquals("不存在该用户", result.getMessage());
    }

    @Test
    public void testGenerateWeeklyReport_Success() {
        WeeklyReportRequest request = new WeeklyReportRequest();
        request.setUserId(1);
        request.setPlayCount(10);
        request.setPlayTotalTime(120);

        Consumer consumer = new Consumer();
        doReturn(consumer).when(consumerMapper).selectById(eq(1));

        doReturn(1).when(weeklyReportMapper).insert(any(WeeklyReport.class));

        R result = weeklyReportService.generateWeeklyReport(request);
        assertEquals("生成周报成功", result.getMessage());
    }

    @Test
    public void testGenerateWeeklyReport_Failure() {
        WeeklyReportRequest request = new WeeklyReportRequest();
        request.setUserId(1);

        Consumer consumer = new Consumer();
        doReturn(consumer).when(consumerMapper).selectById(eq(1));

        doReturn(0).when(weeklyReportMapper).insert(any(WeeklyReport.class));

        R result = weeklyReportService.generateWeeklyReport(request);
        assertEquals("生成周报失败", result.getMessage());
    }

    @Test
    public void testGetWeeklyReport_Success() {
        int userId = 1;
        WeeklyReport weeklyReport = new WeeklyReport();
        doReturn(Collections.singletonList(weeklyReport)).when(weeklyReportMapper).selectList(any());

        R result = weeklyReportService.getWeeklyReport(userId);
        assertEquals("查询成功", result.getMessage());
        assertEquals(Collections.singletonList(weeklyReport), result.getData());
    }

    @Test
    public void testGetWeeklyReport_NoReports() {
        int userId = 1;
        doReturn(Collections.emptyList()).when(weeklyReportMapper).selectList(any());

        R result = weeklyReportService.getWeeklyReport(userId);
        assertEquals("当前没有用户周报", result.getMessage());
    }
}
