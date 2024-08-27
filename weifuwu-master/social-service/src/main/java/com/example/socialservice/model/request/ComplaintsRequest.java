package com.example.socialservice.model.request;

import com.example.socialservice.model.domain.Complaints;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 8:55
 */
@Data
public class ComplaintsRequest {

    private int id;

    private int userId;

    private Complaints.TargetType targetType;

    private int targetId;

    private String reason;

    private Complaints.Status status;

    private Timestamp createAt;

    private Timestamp updateAt;
}
