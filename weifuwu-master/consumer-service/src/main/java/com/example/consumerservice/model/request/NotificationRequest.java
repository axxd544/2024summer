package com.example.consumerservice.model.request;

import lombok.Data;

@Data
public class NotificationRequest {
    private Integer userId;

    private String userType;

    private String message;

    private Integer type;

}
