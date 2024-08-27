package com.example.contentservice.client;

import com.example.contentservice.common.R;
import com.example.contentservice.model.domain.Follow;
import com.example.contentservice.model.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(name="social-service")
public interface SocialClient {
    @GetMapping("/users/followed")
    List<Follow> getFollowersByFollowedId(@RequestParam("followedId") Integer followedId);
    @GetMapping("/users/followed")
    R getFollowersByFollowed(@RequestParam("followedId") Integer followedId);
    @PostMapping("/notifications/add")
    void addNotification(@RequestBody NotificationRequest notificationRequest);

}
