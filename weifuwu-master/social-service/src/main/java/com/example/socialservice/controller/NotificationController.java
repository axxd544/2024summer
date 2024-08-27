package com.example.socialservice.controller;

import com.example.socialservice.common.R;
import com.example.socialservice.model.request.NotificationRequest;
import com.example.socialservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    // 获取管理端消息提醒
    @GetMapping("/manager")
    public R getManagerNotifications() {
        return notificationService.getManagerNotifications();
    }

    // 获取用户消息提醒
    @GetMapping("/consumer")
    public R getConsumerNotifications(@RequestParam int userId) {
        return notificationService.getConsumerNotifications(userId);
    }

    //添加数据到消息表
    @PostMapping("/add")
    public R addNotification(@RequestBody NotificationRequest addNotificationRequest){
        return notificationService.addNotification(addNotificationRequest);
    }


    // 获取消息提醒表
    @GetMapping("/detail")
    public R getNotifications() {
        return notificationService.getNotifications();
    }

    // 标记消息为已读
    @PutMapping("/{id}/read")
    public R markNotificationAsRead(@PathVariable int id) {
        return notificationService.markNotificationAsRead(id);
    }
}
