package com.example.socialservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.socialservice.common.R;
import com.example.socialservice.model.domain.Follow;
import com.example.socialservice.model.request.FollowRequest;
import org.springframework.web.bind.annotation.RequestParam;

public interface FollowService extends IService<Follow> {
    R addFollow(FollowRequest followRequest);
    R deleteFollow(@RequestParam Integer followerId, @RequestParam Integer followedId);

    R listFollowByFollowedId(Integer followedId);

    R listFollowByFollowerId(Integer followedId);

}
