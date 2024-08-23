package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.liu.mapper.*;
import com.example.liu.model.domain.*;
import com.example.liu.model.request.ComplaintStatusUpdateRequest;
import com.example.liu.model.request.ComplaintsRequest;
import com.example.liu.model.request.NotificationRequest;
import com.example.liu.common.R;
import com.example.liu.service.impl.ComplaintsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

public class ComplaintsServiceTest {

    @Mock
    private ComplaintsMapper complaintsMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    @Mock
    private SongMapper songMapper;

    @Mock
    private SongListConsumerMapper songListConsumerMapper;

    @Mock
    private NotificationService notificationService;

    @Mock
    private SingerMapper singerMapper;

    @InjectMocks
    private ComplaintsServiceImpl complaintsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitComplaints_Success() {
        ComplaintsRequest request = new ComplaintsRequest();
        request.setUserId(1);
        request.setTargetId(1);
        request.setTargetType(Complaints.TargetType.SONG);
        request.setReason("Inappropriate content");
        request.setStatus(Complaints.Status.PENDING);

        Consumer consumer = new Consumer();
        consumer.setUsername("User1");

        Song song = new Song();
        song.setName("Song1");
        song.setSingerId(1);

        Singer singer = new Singer();
        singer.setName("Singer1");

        doReturn(consumer).when(consumerMapper).selectById(1);
        doReturn(song).when(songMapper).selectById(1);
        doReturn(singer).when(singerMapper).selectById(1);
        doReturn(1).when(complaintsMapper).insert(any(Complaints.class));

        R result = complaintsService.submitComplaints(request);

        assertEquals("提交投诉成功", result.getMessage());
    }

    @Test
    public void testSubmitComplaints_Failure_UserNotFound() {
        ComplaintsRequest request = new ComplaintsRequest();
        request.setUserId(1);
        request.setTargetId(1);
        request.setTargetType(Complaints.TargetType.SONG);
        request.setReason("Inappropriate content");
        request.setStatus(Complaints.Status.PENDING);

        doReturn(null).when(consumerMapper).selectById(1);

        R result = complaintsService.submitComplaints(request);

        assertEquals("不存在该用户", result.getMessage());
    }


    @Test
    public void testViewAllComplaints_Success() {
        Complaints complaints = new Complaints();
        doReturn(Collections.singletonList(complaints)).when(complaintsMapper).selectList(null);

        R result = complaintsService.viewAllComplaints();

        assertEquals("查询成功", result.getMessage());
    }

    @Test
    public void testViewAllComplaints_Failure() {
        doReturn(Collections.emptyList()).when(complaintsMapper).selectList(null);

        R result = complaintsService.viewAllComplaints();

        assertEquals("当前没有投诉信息", result.getMessage());
    }

    @Test
    public void testGetComplaintsById_Success() {
        Complaints complaints = new Complaints();
        doReturn(Collections.singletonList(complaints)).when(complaintsMapper).selectList(any(QueryWrapper.class));

        R result = complaintsService.getComplaintsById(1);

        assertEquals(1, ((List<?>) result.getData()).size());
    }

    @Test
    public void testGetComplaintsById_Failure() {
        doReturn(Collections.emptyList()).when(complaintsMapper).selectList(any(QueryWrapper.class));

        R result = complaintsService.getComplaintsById(1);

        assertEquals("没有找到对象", result.getMessage());
    }

    @Test
    public void testUpdateComplaintStatus_Success() {
        Complaints complaints = new Complaints();
        doReturn(complaints).when(complaintsMapper).selectById(1);
        doReturn(1).when(complaintsMapper).updateById(complaints);

        ComplaintStatusUpdateRequest request = new ComplaintStatusUpdateRequest();
        request.setStatus(Complaints.Status.REVIEWED);

        R result = complaintsService.updateComplaintStatus(1, request);

        assertEquals("投诉状态更新成功", result.getMessage());
    }

    @Test
    public void testUpdateComplaintStatus_Failure() {
        doReturn(null).when(complaintsMapper).selectById(1);

        ComplaintStatusUpdateRequest request = new ComplaintStatusUpdateRequest();
        request.setStatus(Complaints.Status.REVIEWED);

        R result = complaintsService.updateComplaintStatus(1, request);

        assertEquals("投诉信息不存在", result.getMessage());
    }
}
