package com.example.socialservice.controller;

import com.example.socialservice.common.R;
import com.example.socialservice.model.request.AppealStatusUpdateRequest;
import com.example.socialservice.model.request.AppealsRequest;
import com.example.socialservice.service.AppealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 11:42
 */
@RestController
@RequestMapping
public class AppealsController {
    @Autowired
    private AppealsService appealsService;

    // 提交申诉
    @PostMapping("/appeals")
    public R submitAppeals(@RequestBody AppealsRequest appealsRequest) {
        return appealsService.submitAppeals(appealsRequest);
    }

    // 查看申诉
    @GetMapping("/appeals")
    public R viewAllAppeals() {
        return appealsService.viewAllAppeals();
    }

    // 更新申诉状态
    @PostMapping("/appeals/update")
    public R updateAppealStatus(@RequestParam int id, @RequestBody AppealStatusUpdateRequest appealStatusUpdateRequest) {
        return appealsService.updateAppealStatus(id, appealStatusUpdateRequest);
    }
}
