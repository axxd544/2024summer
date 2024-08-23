package com.example.liu.service;
import com.example.liu.service.impl.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.common.R;
import com.example.liu.mapper.AppealsMapper;
import com.example.liu.mapper.ComplaintsMapper;
import com.example.liu.mapper.ConsumerMapper;
import com.example.liu.model.domain.Appeals;
import com.example.liu.model.domain.Complaints;
import com.example.liu.model.domain.Consumer;
import com.example.liu.model.request.AppealStatusUpdateRequest;
import com.example.liu.model.request.AppealsRequest;
import com.example.liu.model.request.NotificationRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class AppealsServiceTest {

    @Mock
    private AppealsMapper appealsMapper;

    @Mock
    private ComplaintsMapper complaintsMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private AppealsServiceImpl appealsService;

    Date date = new Date();
    Timestamp timestamp = new Timestamp(date.getTime());

    public AppealsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitAppeals_Success() {
        AppealsRequest request = new AppealsRequest();
        request.setComplaintId(1);
        request.setUserId(1);
        request.setReason("Test reason");

        request.setCreateAt(timestamp);
        request.setUpdateAt(timestamp);

        Complaints complaint = new Complaints();
        complaint.setId(1);
        doReturn(Collections.singletonList(complaint)).when(complaintsMapper).selectList((QueryWrapper<Complaints>) any());

        Consumer consumer = new Consumer();
        consumer.setId(1);
        consumer.setUsername("TestUser");
        doReturn(consumer).when(consumerMapper).selectById(1);

        doReturn(1).when(appealsMapper).insert((Appeals) any());

        NotificationRequest notificationRequest1 = new NotificationRequest();
        notificationRequest1.setUserId(null);
        notificationRequest1.setUserType("manager");
        notificationRequest1.setMessage("用户 \"1-TestUser\" 对投诉信息1提出申诉。");
        notificationRequest1.setType(3);

        NotificationRequest notificationRequest2 = new NotificationRequest();
        notificationRequest2.setUserId(1);
        notificationRequest2.setUserType("consumer");
        notificationRequest2.setMessage("您的申诉信息已提交。");
        notificationRequest2.setType(3);

    }

    @Test
    public void testSubmitAppeals_Failure_ComplaintNotFound() {
        AppealsRequest request = new AppealsRequest();
        request.setComplaintId(1);
        request.setUserId(1);
        request.setReason("Test reason");

        request.setCreateAt(timestamp);
        request.setUpdateAt(timestamp);

        doReturn(Collections.emptyList()).when(complaintsMapper).selectList((QueryWrapper<Complaints>) any());

        R response = appealsService.submitAppeals(request);

        assertEquals(R.error("不存在该投诉信息"), response);
    }

    @Test
    public void testSubmitAppeals_Failure_UserNotFound() {
        AppealsRequest request = new AppealsRequest();
        request.setComplaintId(1);
        request.setUserId(1);
        request.setReason("Test reason");

        request.setCreateAt(timestamp);
        request.setUpdateAt(timestamp);

        Complaints complaint = new Complaints();
        complaint.setId(1);
        doReturn(Collections.singletonList(complaint)).when(complaintsMapper).selectList((QueryWrapper<Complaints>) any());

        doReturn(null).when(consumerMapper).selectById(1);

        R response = appealsService.submitAppeals(request);

        assertEquals(R.error("不存在该用户"), response);
    }

    @Test
    public void testViewAllAppeals() {
        Appeals appeal = new Appeals();
        appeal.setId(1);
        doReturn(Collections.singletonList(appeal)).when(appealsMapper).selectList(null);

        R response = appealsService.viewAllAppeals();

        assertEquals(R.success("查询成功", Collections.singletonList(appeal)), response);
    }

    @Test
    public void testUpdateAppealStatus_Success() {
        AppealStatusUpdateRequest updateRequest = new AppealStatusUpdateRequest();
        updateRequest.setUpdateAt(timestamp);

        Appeals appeal = new Appeals();
        appeal.setId(1);
        doReturn(appeal).when(appealsMapper).selectById(1);
        doReturn(1).when(appealsMapper).updateById((Appeals) any());

        R response = appealsService.updateAppealStatus(1, updateRequest);

        assertEquals(R.success("申诉状态更新成功"), response);
    }

    @Test
    public void testUpdateAppealStatus_Failure() {
        AppealStatusUpdateRequest updateRequest = new AppealStatusUpdateRequest();

        updateRequest.setUpdateAt(timestamp);

        doReturn(null).when(appealsMapper).selectById(1);

        R response = appealsService.updateAppealStatus(1, updateRequest);

        assertEquals(R.error("申诉信息不存在"), response);
    }

    @Test
    public void testUpdateAppealStatus_Exception() {
        AppealStatusUpdateRequest updateRequest = new AppealStatusUpdateRequest();

        updateRequest.setUpdateAt(timestamp);

        Appeals appeal = new Appeals();
        appeal.setId(1);
        doReturn(appeal).when(appealsMapper).selectById(1);
        doThrow(new RuntimeException("Database error")).when(appealsMapper).updateById((Appeals) any());

        R response = appealsService.updateAppealStatus(1, updateRequest);

        assertEquals(R.error("出现错误:Database error"), response);
    }
}
