package com.example.consumerservice.controller;

import com.example.consumerservice.common.R;
import com.example.consumerservice.model.request.PlayHistoryRequest;
import com.example.consumerservice.service.PlayHistoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 544
 * @Description:
 * @date 2024/5/29 9:06
 */
@RestController
@Api(tags="播放历史接口")
public class PlayHistoryController {
    @Autowired
    private PlayHistoryService playHistoryService;

    // 记录播放历史
    @PostMapping("/playHistory")
    public R recodePlayHistory(@RequestBody PlayHistoryRequest playHistoryRequest) {
        return playHistoryService.recodePlayHistory(playHistoryRequest);
    }

    // 获取用户播放历史
    @GetMapping("playHistory/user")
    public R getPlayHistory(@RequestParam int userId) {
        return playHistoryService.getPlayHistory(userId);
    }

}
