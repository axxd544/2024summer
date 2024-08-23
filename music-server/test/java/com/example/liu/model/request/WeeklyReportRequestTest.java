package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class WeeklyReportRequestTest {

    @Test
    public void testWeeklyReportRequest_WithValidInput() {
        WeeklyReportRequest request = new WeeklyReportRequest();
        request.setUserId(1);
        request.setPlayCount(10);
        request.setPlayTotalTime(300);
        request.setWeekStartDate(Date.valueOf("2024-08-01"));
        request.setWeekEndDate(Date.valueOf("2024-08-07"));

        assertThat(request.getUserId()).isEqualTo(1);
        assertThat(request.getPlayCount()).isEqualTo(10);
        assertThat(request.getPlayTotalTime()).isEqualTo(300);
        assertThat(request.getWeekStartDate()).isEqualTo(Date.valueOf("2024-08-01"));
        assertThat(request.getWeekEndDate()).isEqualTo(Date.valueOf("2024-08-07"));
    }

    @Test
    public void testWeeklyReportRequest_WithNullValues() {
        WeeklyReportRequest request = new WeeklyReportRequest();

        assertThat(request.getUserId()).isEqualTo(0);
        assertThat(request.getPlayCount()).isEqualTo(0);
        assertThat(request.getPlayTotalTime()).isEqualTo(0);
        assertThat(request.getWeekStartDate()).isNull();
        assertThat(request.getWeekEndDate()).isNull();
    }
}
