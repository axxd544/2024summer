package com.example.socialservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.socialservice.client.ConsumerClient;
import com.example.socialservice.common.R;
import com.example.socialservice.mapper.AppealsMapper;
import com.example.socialservice.mapper.ComplaintsMapper;
import com.example.socialservice.model.domain.Appeals;
import com.example.socialservice.model.domain.Complaints;
import com.example.socialservice.model.domain.Consumer;
import com.example.socialservice.model.request.AppealStatusUpdateRequest;
import com.example.socialservice.model.request.AppealsRequest;
import com.example.socialservice.model.request.NotificationRequest;
import com.example.socialservice.service.AppealsService;
import com.example.socialservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 11:52
 */
@Service
public class AppealsServiceImpl extends ServiceImpl<AppealsMapper, Appeals> implements AppealsService {

    @Autowired
    private AppealsMapper appealsMapper;
    @Autowired
    private ComplaintsMapper complaintsMapper;
    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private NotificationService notificationService;
    @Override
    public R submitAppeals(AppealsRequest appealsRequest) {
        Appeals appeals = new Appeals();

        int complaintId = appealsRequest.getComplaintId();
        QueryWrapper<Complaints> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", complaintId);
        List<Complaints> result = complaintsMapper.selectList(queryWrapper);
        if (result == null || result.isEmpty()) {
            return R.error("不存在该投诉信息");
        }
        appeals.setComplaintId(complaintId);

        int userId = appealsRequest.getUserId();
        Consumer consumer = consumerClient.userOfId(userId);
        if (consumer == null) {
            return R.error("不存在该用户");
        }
        appeals.setUserId(userId);
        appeals.setReason(appealsRequest.getReason());
        appeals.setStatus(appealsRequest.getStatus());
        appeals.setCreateAt(appealsRequest.getCreateAt());
        appeals.setUpdateAt(appealsRequest.getUpdateAt());

        if (appealsMapper.insert(appeals) > 0) {
            //获取申诉者id
            Integer appealerId = 0;
            String appealerName = "";
            appealerId = appeals.getUserId();
            //QueryWrapper<Consumer> consumerQueryWrapper = new QueryWrapper<>();
            //consumerQueryWrapper.eq("id", appealerId);
            appealerName = consumerClient.userOfId(appealerId).getUsername();


            //管理端
            NotificationRequest notificationRequest2 = new NotificationRequest();
            notificationRequest2.setUserId(null);
            notificationRequest2.setUserType("manager");
            notificationRequest2.setMessage("用户 \"" + appealerId + "-" + appealerName + "\" 对投诉信息" + appeals.getComplaintId() + "提出申诉。");
            notificationRequest2.setType(3);
            notificationService.addNotification(notificationRequest2);

            //申诉者
            NotificationRequest notificationRequest3 = new NotificationRequest();
            notificationRequest3.setUserId(appealerId);
            notificationRequest3.setUserType("consumer");
            notificationRequest3.setMessage("您的申诉信息已提交。");
            notificationRequest3.setType(3);
            notificationService.addNotification(notificationRequest3);
            return R.success("提交申诉成功");
        } else {
            return R.error("提交申诉失败");
        }
    }

    @Override
    public R viewAllAppeals() {
        List<Appeals> allAppeals = appealsMapper.selectList(null);
        if (allAppeals == null || allAppeals.size() == 0) {
            return R.error("当前没有申诉信息");
        } else {
            return R.success("查询成功", allAppeals);
        }
    }

    @Override
    public R updateAppealStatus(int id, AppealStatusUpdateRequest appealStatusUpdateRequest) {
        try {
            Appeals appeals = appealsMapper.selectById(id);
            if (appeals == null) {
                return R.error("申诉信息不存在");
            }
            appeals.setStatus(appealStatusUpdateRequest.getStatus());
            appeals.setUpdateAt(appealStatusUpdateRequest.getUpdateAt());
            if (appealsMapper.updateById(appeals) > 0) {
                return R.success("申诉状态更新成功");
            } else {
                return R.error("申诉状态更新失败");
            }
        } catch (Exception e) {
            return R.error("出现错误:" + e.getMessage());
        }
    }
}
