package com.example.contentservice.model.request;

import lombok.Data;

@Data
public class FollowRequest {
    private Integer id;

    private Integer followerId;

    private Integer followedId;
}
