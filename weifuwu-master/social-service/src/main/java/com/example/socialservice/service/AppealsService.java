package com.example.socialservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.socialservice.common.R;
import com.example.socialservice.model.domain.Appeals;
import com.example.socialservice.model.request.AppealStatusUpdateRequest;
import com.example.socialservice.model.request.AppealsRequest;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 11:48
 */
public interface AppealsService extends IService<Appeals> {
    // 提交申诉
    R submitAppeals(AppealsRequest appealsRequest);

    // 查看申诉
    R viewAllAppeals();

    // 更新申诉状态
    R updateAppealStatus(int id, AppealStatusUpdateRequest appealStatusUpdateRequest);
}
