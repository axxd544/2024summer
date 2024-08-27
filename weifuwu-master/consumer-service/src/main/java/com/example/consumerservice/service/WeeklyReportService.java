package com.example.consumerservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.consumerservice.common.R;
import com.example.consumerservice.model.domain.WeeklyReport;
import com.example.consumerservice.model.request.WeeklyReportRequest;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 17:13
 */
public interface WeeklyReportService extends IService<WeeklyReport> {
    // 生成周报
    R generateWeeklyReport(WeeklyReportRequest weeklyReportRequest);

    // 获取周报
    R getWeeklyReport(int userId);
}
