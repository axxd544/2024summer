package com.example.liu.model.domain;


import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class WeeklyReportTest {

    @Test
    public void testWeeklyReport() {
        WeeklyReport weeklyReport = new WeeklyReport();

        // 初始化对象，断言初始值为默认值
        assertEquals(0, weeklyReport.getId());
        assertEquals(0, weeklyReport.getUserId());
        assertEquals(0, weeklyReport.getPlayCount());
        assertEquals(0, weeklyReport.getPlayTotalTime());
        assertNull(weeklyReport.getWeekStartDate());
        assertNull(weeklyReport.getWeekEndDate());

        // 设置属性值，断言设置值成功
        weeklyReport.setId(1);
        weeklyReport.setUserId(100);
        weeklyReport.setPlayCount(50);
        weeklyReport.setPlayTotalTime(3600);
        Date weekStartDate = Date.valueOf("2024-08-01");
        weeklyReport.setWeekStartDate(weekStartDate);
        Date weekEndDate = Date.valueOf("2024-08-07");
        weeklyReport.setWeekEndDate(weekEndDate);

        assertEquals(1, weeklyReport.getId());
        assertEquals(100, weeklyReport.getUserId());
        assertEquals(50, weeklyReport.getPlayCount());
        assertEquals(3600, weeklyReport.getPlayTotalTime());
        assertEquals(weekStartDate, weeklyReport.getWeekStartDate());
        assertEquals(weekEndDate, weeklyReport.getWeekEndDate());
    }

    @Test
    public void testToString() {
        WeeklyReport weeklyReport = new WeeklyReport();
        weeklyReport.setId(1);
        weeklyReport.setUserId(100);
        weeklyReport.setPlayCount(50);
        weeklyReport.setPlayTotalTime(3600);
        Date weekStartDate = Date.valueOf("2024-08-01");
        weeklyReport.setWeekStartDate(weekStartDate);
        Date weekEndDate = Date.valueOf("2024-08-07");
        weeklyReport.setWeekEndDate(weekEndDate);

        String weeklyReportString = weeklyReport.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(weeklyReportString.contains("id=1"));
        assertTrue(weeklyReportString.contains("userId=100"));
        assertTrue(weeklyReportString.contains("playCount=50"));
        assertTrue(weeklyReportString.contains("playTotalTime=3600"));
        assertTrue(weeklyReportString.contains("weekStartDate=" + weekStartDate.toString()));
        assertTrue(weeklyReportString.contains("weekEndDate=" + weekEndDate.toString()));
    }

    @Test
    public void testEqualsAndHashCode() {
        WeeklyReport report1 = new WeeklyReport();
        report1.setId(1);
        report1.setUserId(100);
        report1.setPlayCount(50);
        report1.setPlayTotalTime(3600);
        report1.setWeekStartDate(Date.valueOf("2024-08-01"));
        report1.setWeekEndDate(Date.valueOf("2024-08-07"));

        WeeklyReport report2 = new WeeklyReport();
        report2.setId(1);
        report2.setUserId(100);
        report2.setPlayCount(50);
        report2.setPlayTotalTime(3600);
        report2.setWeekStartDate(Date.valueOf("2024-08-01"));
        report2.setWeekEndDate(Date.valueOf("2024-08-07"));

        WeeklyReport report3 = new WeeklyReport();
        report3.setId(2);
        report3.setUserId(200);
        report3.setPlayCount(100);
        report3.setPlayTotalTime(7200);
        report3.setWeekStartDate(Date.valueOf("2024-08-08"));
        report3.setWeekEndDate(Date.valueOf("2024-08-14"));

        // 测试equals方法
        assertEquals(report1, report2);
        assertNotEquals(report1, report3);

        // 测试hashCode方法
        assertEquals(report1.hashCode(), report2.hashCode());
        assertNotEquals(report1.hashCode(), report3.hashCode());
    }
}

