package com.example.consumerservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.consumerservice.common.R;
import com.example.consumerservice.model.domain.PlayHistory;
import com.example.consumerservice.model.request.PlayHistoryRequest;

/**
 * @author 544
 * @Description:
 * @date 2024/5/29 9:21
 */
public interface PlayHistoryService extends IService<PlayHistory> {
    R recodePlayHistory(PlayHistoryRequest playHistoryRequest);

    R getPlayHistory(int userId);
}
