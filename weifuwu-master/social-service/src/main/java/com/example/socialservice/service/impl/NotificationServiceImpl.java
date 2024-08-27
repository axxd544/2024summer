package com.example.socialservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.socialservice.common.R;
import com.example.socialservice.mapper.NotificationMapper;
import com.example.socialservice.model.domain.Notification;
import com.example.socialservice.model.request.NotificationRequest;
import com.example.socialservice.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public R getManagerNotifications() {
        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_type", "manager");
        return R.success(null,notificationMapper.selectList(queryWrapper));
    }

    @Override
    public R getConsumerNotifications(int userId) {
        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("user_type", "consumer");
        return R.success(null,notificationMapper.selectList(queryWrapper));

    }

    @Override
    public R getNotifications() {
        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
        return R.success(null,notificationMapper.selectList(queryWrapper));
    }

    @Override
    public R addNotification(NotificationRequest addNotificationRequest){
        Notification notification = new Notification();
        BeanUtils.copyProperties(addNotificationRequest, notification);

        // 插入记录以生成 createdAt 时间戳
        if (notificationMapper.insert(notification) > 0) {
            // 获取插入后的通知
            Notification insertedNotification = notificationMapper.selectById(notification.getId());


            // 数据库中最新的消息（除去刚插入的消息）
            QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("id", notification.getId()).orderByDesc("id").last("LIMIT 1");
            Notification latestNotification = notificationMapper.selectOne(queryWrapper);

            // 检查是否存在同类最新消息，以及时间戳差异是否小于1秒
            if (latestNotification != null) {
                if ((latestNotification.getUserType().equals("manager") && notification.getUserType().equals("manager"))
                        || (latestNotification.getUserType().equals("consumer") && notification.getUserType().equals("consumer") && Objects.equals(latestNotification.getUserId(), notification.getUserId()))
                ){
                    long timeDifference = Math.abs(insertedNotification.getCreatedAt().getTime() - latestNotification.getCreatedAt().getTime());
                    if (timeDifference < 2000) {  // 2秒
                        // 删除刚插入的记录
                        notificationMapper.deleteById(insertedNotification.getId());
                        return R.error("添加信息失败，时间间隔小于2秒");
                    }
                }

            }
            return R.success("添加信息成功");
        } else {
            return R.error("添加信息失败");
        }

    }

    @Override
    public R markNotificationAsRead(int id) {
        Notification notification = notificationMapper.selectById(id);
        if (notification == null) {
            return R.error("通知不存在");
        }
        notification.setIsRead(true);
        if (notificationMapper.updateById(notification) > 0) {
            return R.success("标记成功");
        } else {
            return R.error("标记失败");
        }
    }
}
