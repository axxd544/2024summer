package com.example.socialservice.controller;

import com.example.socialservice.common.R;
import com.example.socialservice.model.request.FollowRequest;
import com.example.socialservice.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class FollowController {

    @Autowired
    private FollowService followService;

    // 关注用户
    @PostMapping("/follow")
    public R addFollow(@RequestBody FollowRequest followRequest) {
        return followService.addFollow(followRequest);
    }

    // 取消关注
    @GetMapping("/follow")
    public R deleteFollow(@RequestParam Integer followerId, @RequestParam Integer followedId) {
        return followService.deleteFollow(followerId, followedId);
    }

    //返回用户的被关注列表
    @GetMapping("/followed")
    public R listFollowByFollowedId(@RequestParam Integer followedId) {
        return followService.listFollowByFollowedId(followedId);
    }

    //返回用户的关注列表
    @GetMapping("/follower")
    public R listFollowByFollowerId(@RequestParam Integer followerId) {
        return followService.listFollowByFollowerId(followerId);
    }




}
