package com.example.socialservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.socialservice.common.R;
import com.example.socialservice.model.domain.Complaints;
import com.example.socialservice.model.request.ComplaintStatusUpdateRequest;
import com.example.socialservice.model.request.ComplaintsRequest;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 8:58
 */
public interface ComplaintsService extends IService<Complaints> {
    // 提交投诉
    R submitComplaints(ComplaintsRequest complaintsRequest);

    // 查看投诉
    R viewAllComplaints();

    R getComplaintsById(int id);

    // 更新投诉状态
    R updateComplaintStatus(int id, ComplaintStatusUpdateRequest complaintStatusUpdateRequest);
}
