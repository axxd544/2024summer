package com.example.socialservice.controller;

import com.example.socialservice.common.R;
import com.example.socialservice.model.request.ComplaintStatusUpdateRequest;
import com.example.socialservice.model.request.ComplaintsRequest;
import com.example.socialservice.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 8:48
 */
@RestController
@RequestMapping
public class ComplaintsController {
    @Autowired
    private ComplaintsService complaintsService;

    // 提交投诉
    @PostMapping("/complaints")
    public R submitComplaints(@RequestBody ComplaintsRequest complaintsRequest) {
        return complaintsService.submitComplaints(complaintsRequest);
    }

    // 查看投诉
    @GetMapping("/complaints")
    public R viewAllComplaints() {
        return complaintsService.viewAllComplaints();
    }

    // 查看投诉byId
    @GetMapping("/complaint/byId")
    public R getComplaintsById(int id) {
        return complaintsService.getComplaintsById(id);
    }

    // 更新投诉状态
    @PostMapping("/complaints/update")
    public R updateComplaintStatus(@RequestParam int id, @RequestBody ComplaintStatusUpdateRequest complaintStatusUpdateRequest) {
        return complaintsService.updateComplaintStatus(id, complaintStatusUpdateRequest);
    }

    //

}
