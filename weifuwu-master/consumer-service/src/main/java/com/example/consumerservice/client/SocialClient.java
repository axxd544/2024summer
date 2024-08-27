package com.example.consumerservice.client;

import com.example.consumerservice.model.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="social-service")
public interface SocialClient {

    @PostMapping("/notifications/add")
    void addNotification(@RequestBody NotificationRequest notificationRequest);

}
